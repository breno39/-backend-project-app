package com.ninjaone.backendinterviewproject.service.domain;

import com.ninjaone.backendinterviewproject.commons.domain.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Service extends AbstractEntity {
    private Integer cost;
    private ServiceType type;

    public Service(Integer cost, ServiceType type) {
        this.cost = cost;
        this.type = type;
    }
}
