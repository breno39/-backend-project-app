package com.ninjaone.backendinterviewproject.device.domain;

import com.ninjaone.backendinterviewproject.commons.domain.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Device extends AbstractEntity {
    private String systemName;
    private DeviceType type;
}
