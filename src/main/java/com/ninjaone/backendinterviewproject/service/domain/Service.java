package com.ninjaone.backendinterviewproject.service.domain;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(columnList = "type"))
public class Service {
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

    @Setter
    private Integer cost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private ServiceType type;

    public Service(ServiceType type) {
        this.type = type;
        this.cost = type.getCost();
    }
}
