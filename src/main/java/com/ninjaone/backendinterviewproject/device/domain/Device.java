package com.ninjaone.backendinterviewproject.device.domain;

import com.ninjaone.backendinterviewproject.handler.ApiException;
import com.ninjaone.backendinterviewproject.service.domain.Service;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;

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

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Builder.Default
    private Long totalMonthlyCost = DEVICE_COST;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ElementCollection(targetClass=Service.class)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Service> services = new HashSet<>();

    public void addService(Service service) {
        checkServiceCompatibility(service.getType());
        this.services.add(service);
        this.calculateTotalMonthlyCost();
    }

    public void RemoveService(Service service) {
        this.services.remove(service);
        this.calculateTotalMonthlyCost();
    }

    public void setServices(Set<Service> services) {
        this.services = services;
        this.calculateTotalMonthlyCost();
    }

    private void checkServiceCompatibility(ServiceType type) {
        if(!isCompatible(type)) {
            throw ApiException.throwApiException(HttpStatus.BAD_REQUEST, "Service and Device are not compatible");
        }
    }

    public boolean isCompatible(ServiceType type) {
        return this.type.getSystem() == type.getSystem();
    }

    private void calculateTotalMonthlyCost() {
        totalMonthlyCost = services.stream()
                .map(Service::getMonthlyCost)
                .reduce(DEVICE_COST, Long::sum);
    }
}
