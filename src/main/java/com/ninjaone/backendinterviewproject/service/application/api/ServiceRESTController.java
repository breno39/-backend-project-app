package com.ninjaone.backendinterviewproject.service.application.api;

import com.ninjaone.backendinterviewproject.service.application.service.ServiceService;
import com.ninjaone.backendinterviewproject.service.domain.Service;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "openApiAuth")
public class ServiceRESTController implements ServiceAPI{
    private static final String SERVICE_CREATED_PATH = "ninjaone/app/v1/service/";
    private final Logger logger = LoggerFactory.getLogger(ServiceRESTController.class);
    private final ServiceService service;

    @Override
    public ResponseEntity<Object> getAvailableServices() {
        logger.info("[START] - ServiceRESTController - getAvailableServices");
        var services = service.getAvailableServices();
        logger.info("[FINISH] - ServiceRESTController - getAvailableServices");
        return ResponseEntity.ok(services);
    }

    @Override
    public ResponseEntity<ServiceDTO> addAvailableServiceToDevice(ServiceForm serviceForm, UUID deviceId,
                                                                  UriComponentsBuilder uriBuilder) {
        logger.info("[START] - ServiceRESTController - addAvailableServiceToDevice");
        Service createdService = service.addAvailableServiceToDevice(serviceForm.getType(), deviceId);
        URI uri = uriBuilder.path(SERVICE_CREATED_PATH.concat(createdService.getId().toString()))
                        .buildAndExpand(createdService.getId()).toUri();
        logger.info("[FINISH] - ServiceRESTController - addAvailableServiceToDevice");
        return ResponseEntity.created(uri).body(new ServiceDTO(createdService));
    }

    @Override
    public void removeAvailableServiceFromDevice(ServiceForm serviceForm, UUID deviceId) {
        logger.info("[START] - ServiceRESTController - removeAvailableServiceFromDevice");
        service.removeAvailableServiceFromDevice(serviceForm.getType(), deviceId);
        logger.info("[FINISH] - ServiceRESTController - removeAvailableServiceFromDevice");
    }
}
