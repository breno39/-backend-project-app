package com.ninjaone.backendinterviewproject.service.domain;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Service {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @EqualsAndHashCode.Include
    private UUID id;

    @Setter
    @JoinColumn(name = "fk_device")
    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    private Long MonthlyCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    @EqualsAndHashCode.Include
    private ServiceType type;

    public Service(ServiceType type) {
        this.type = type;
        this.MonthlyCost = type.getCost();
    }

    public boolean isType(ServiceType type) {
        return this.type.equals(type);
    }
}
