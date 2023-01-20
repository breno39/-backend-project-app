package com.breno39.backendproject.service.domain;

import com.breno39.backendproject.service.domain.domain.OperatingSystem;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceType {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;

    private Long cost;

    @ManyToOne
    @JoinColumn(name = "system_id")
    private OperatingSystem system;

}
