package com.ninjaone.backendinterviewproject.service.application.service;

import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ServiceService {
    Set<ServiceType> getAvailableServices();
}
