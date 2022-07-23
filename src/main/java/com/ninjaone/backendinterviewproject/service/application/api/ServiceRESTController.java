package com.ninjaone.backendinterviewproject.service.application.api;

import com.ninjaone.backendinterviewproject.service.application.service.ServiceService;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ServiceRESTController implements ServiceAPI{
    private Logger logger = LoggerFactory.getLogger(ServiceRESTController.class);
    private ServiceService service;

    public ServiceRESTController(ServiceService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Object> getAvailableServices() {
        logger.info("[START] - ServiceRESTController - getAvailableServices");
        Set<ServiceType> serviceSet = service.getAvailableServices();
        logger.info("[FINISH] - ServiceRESTController - getAvailableServices");
        return ResponseEntity.ok(serviceSet);
    }

    @Override
    public void addAvailableServiceToDevice() {
        logger.info("[START] - ServiceRESTController - getAvailableServices");
        logger.info("[FINISH] - ServiceRESTController - getAvailableServices");
    }

    @Override
    public void removeAvailableServiceFromDevice() {
        logger.info("[START] - ServiceRESTController - getAvailableServices");
        logger.info("[FINISH] - ServiceRESTController - getAvailableServices");
    }
}
