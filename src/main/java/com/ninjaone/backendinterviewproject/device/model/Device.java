package com.ninjaone.backendinterviewproject.device.domain.model;

import com.ninjaone.backendinterviewproject.commons.domain.AbstractEntity;
import com.ninjaone.backendinterviewproject.service.domain.Service;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Device extends AbstractEntity {
    private String systemName;

    private DeviceType type;

    @ElementCollection(targetClass=Service.class)
    @EqualsAndHashCode.Exclude
    @OneToMany
    private Set<Service> services;
}
