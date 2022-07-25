package com.ninjaone.backendinterviewproject.service.application.api;

import com.ninjaone.backendinterviewproject.service.application.service.ServiceService;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ServiceRESTController implements ServiceAPI{
    private Logger logger = LoggerFactory.getLogger(ServiceRESTController.class);
    private final ServiceService service;

    @Override
    public ResponseEntity<Object> getAvailableServices() {
        logger.info("[START] - ServiceRESTController - getAvailableServices");
        Set<ServiceType> serviceSet = service.getAvailableServices();
        logger.info("[FINISH] - ServiceRESTController - getAvailableServices");
        return ResponseEntity.ok(serviceSet);
    }

    @Override
    public void addAvailableServiceToDevice(ServiceForm serviceForm, UUID deviceId, UUID customerId) {
        logger.info("[START] - ServiceRESTController - getAvailableServices");
        service.addAvailableServiceToDevice(serviceForm.getType(), deviceId, customerId);
        logger.info("[FINISH] - ServiceRESTController - getAvailableServices");
    }

    @Override
    public void removeAvailableServiceFromDevice(ServiceForm serviceForm, UUID deviceId, UUID customerId) {
        logger.info("[START] - ServiceRESTController - getAvailableServices");
        service.removeAvailableServiceFromDevice(serviceForm.getType(), deviceId, customerId);
        logger.info("[FINISH] - ServiceRESTController - getAvailableServices");
    }
}
