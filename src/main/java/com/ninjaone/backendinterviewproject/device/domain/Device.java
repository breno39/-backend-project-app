package com.ninjaone.backendinterviewproject.device.domain;

import com.ninjaone.backendinterviewproject.customer.domain.Customer;
import com.ninjaone.backendinterviewproject.service.domain.Service;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode()
public class Device {
    public static final Long DEVICE_COST = 4L;

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

    @Column(unique = true)
    private String systemName;

    private DeviceType type;

    @Builder.Default
    private Long totalMonthlyCost = DEVICE_COST;

    @ElementCollection(targetClass=Service.class)
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Service> services;

    public void addService(Service service) {
        this.services.add(service);
        this.calculateTotalMonthlyCost();
    }

    public void RemoveService(Service service) {
        this.services.remove(service);
        this.calculateTotalMonthlyCost();
    }

    private void calculateTotalMonthlyCost() {
        totalMonthlyCost = services.stream()
                .map(Service::getMonthlyCost)
                .reduce(DEVICE_COST, Long::sum);
    }

    public void setServices(Set<Service> services) {
        this.services = services;
        this.calculateTotalMonthlyCost();
    }
}
