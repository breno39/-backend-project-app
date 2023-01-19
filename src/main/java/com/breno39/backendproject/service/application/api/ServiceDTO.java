package com.breno39.backendproject.service.application.api;

import com.breno39.backendproject.service.domain.Service;
import com.breno39.backendproject.service.domain.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    private UUID id;
    private ServiceType type;
    private Long monthlyCost;

    public ServiceDTO(Service createdService) {
        id = createdService.getId();
        type = createdService.getType();
        monthlyCost = createdService.getMonthlyCost();
    }
}
