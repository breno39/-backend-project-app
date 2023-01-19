package com.breno39.backendinterviewproject.service.application.api;

import com.breno39.backendinterviewproject.service.application.service.ServiceService;
import com.breno39.backendinterviewproject.service.domain.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "openApiAuth")
@Tag(name = "Services API", description = "Contains the CRUD of the Service domain")
public class ServiceRESTController implements ServiceAPI{
    private static final String SERVICE_CREATED_PATH = "ninjaone/app/v1/service/";
    private final Logger logger = LoggerFactory.getLogger(ServiceRESTController.class);
    private final ServiceService service;

    @Override
    @Operation(operationId = "getAvailableServices", description = "End-point used to list the available service types", summary = "List available services")
    public ResponseEntity<Object> getAvailableServices() {
        logger.info("[START] - ServiceRESTController - getAvailableServices");
        var services = service.getAvailableServices();
        logger.info("[FINISH] - ServiceRESTController - getAvailableServices");
        return ResponseEntity.ok(services);
    }

    @Override
    @Operation(operationId = "addAvailableServiceToDevice", description = "End-point used to add one of the available service types into an existing Device, the device and Service must be compatible", summary = "add service into Device")
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
    @Operation(operationId = "removeAvailableServiceFromDevice", description = "End-point used to remove one of the available service types of an existing Device", summary = "remove service from device")
    public void removeAvailableServiceFromDevice(ServiceForm serviceForm, UUID deviceId) {
        logger.info("[START] - ServiceRESTController - removeAvailableServiceFromDevice");
        service.removeAvailableServiceFromDevice(serviceForm.getType(), deviceId);
        logger.info("[FINISH] - ServiceRESTController - removeAvailableServiceFromDevice");
    }
}
