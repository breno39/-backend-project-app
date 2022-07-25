package com.ninjaone.backendinterviewproject.service.application.service;

import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public interface ServiceService {
    Set<ServiceType> getAvailableServices();

    com.ninjaone.backendinterviewproject.service.domain.Service addAvailableServiceToDevice(ServiceType service, UUID deviceId, UUID customerId);

    void removeAvailableServiceFromDevice(ServiceType type, UUID deviceId, UUID customerId);
}
