package com.ninjaone.backendinterviewproject.service.application.api;

import com.ninjaone.backendinterviewproject.service.domain.Service;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
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
    private Long MonthlyCost;

    public ServiceDTO(Service createdService) {
        id = createdService.getId();
        type = createdService.getType();
        MonthlyCost = createdService.getMonthlyCost();
    }
}
