package com.breno39.backendproject.service.domain.domain;

import com.breno39.backendproject.customer.domain.Customer;
import com.breno39.backendproject.service.domain.Service;
import com.breno39.backendproject.service.domain.ServiceType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NamedQuery(name = "Device.getTotalMonthlyCostById",
        query = "SELECT d.totalMonthlyCost FROM Device d WHERE d.id = :deviceId"
)
public class Device {
    public static final Long DEVICE_COST = 4L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @EqualsAndHashCode.Include
    private UUID id;

    @Setter
    @JoinColumn(name = "fk_customer")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Column(unique = true)
    @EqualsAndHashCode.Include
    private String systemName;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private DeviceType type;

    @Builder.Default
    private Long totalMonthlyCost = DEVICE_COST;

    @Builder.Default
    @ElementCollection(targetClass= Service.class)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "device", orphanRemoval = true)
    private Set<Service> services = new HashSet<>();

    public void addService(Service service) {
        if(!isCompatible(service.getType())) {
            return;
        }

        this.services.add(service);
        service.setDevice(this);
        this.calculateTotalMonthlyCost();
    }

    public void removeService(Service service) {
        this.services.remove(service);
        service.setDevice(null);
        this.calculateTotalMonthlyCost();
    }

    public boolean isCompatible(ServiceType type) {
        return this.type.getSystem() == type.getSystem()
                || OperatingSystem.ANY.equals(type.getSystem().getCompatibilityType());
    }

    public Optional<Service> getServiceByServiceType(ServiceType type) {
        return this.services.stream().filter(service -> service.isType(type)).findFirst();
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
