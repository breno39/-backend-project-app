package com.ninjaone.backendinterviewproject.customer.domain;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.service.domain.Service;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode()
public class Customer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Builder.Default
    private Long totalMonthlyCost = 0L;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ElementCollection(targetClass= Device.class)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    private Set<Device> devices = new HashSet<>();

    public void addDevice(Device device) {
        this.devices.add(device);
        device.setCustomer(this);
        this.calculateTotalMonthlyCost();
    }

    public void removeDevice(Device device) {
        this.devices.remove(device);
        device.setCustomer(null);
        this.calculateTotalMonthlyCost();
    }

    private void calculateTotalMonthlyCost() {
        totalMonthlyCost = devices.stream()
                .map(Device::getTotalMonthlyCost)
                .reduce(0L, Long::sum);
    }
}
