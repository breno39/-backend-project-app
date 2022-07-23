package com.ninjaone.backendinterviewproject.service.application.service;

import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceSpringDataJPAService implements ServiceService{

    @Override
    public Set<ServiceType> getAvailableServices() {
        return Arrays.stream(ServiceType.values()).collect(Collectors.toSet());
    }
}
