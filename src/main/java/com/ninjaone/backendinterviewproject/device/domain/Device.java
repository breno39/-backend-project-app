package com.ninjaone.backendinterviewproject.device.domain;

import com.ninjaone.backendinterviewproject.commons.domain.AbstractEntity;
import com.ninjaone.backendinterviewproject.service.domain.Service;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Device extends AbstractEntity {
    private String systemName;

    private DeviceType type;

    @ElementCollection(targetClass=Service.class)
    private Set<Service> services;
}
