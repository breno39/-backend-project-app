package com.breno39.backendinterviewproject.device.application.api;

import com.breno39.backendinterviewproject.device.application.service.DeviceService;
import com.breno39.backendinterviewproject.device.domain.Device;
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
@Tag(name = "Devices API", description = "Contains the CRUD of the Service domain, can be use to get the total monthly cost of a single device, also contains endpoints to add/remove Services")
public class DeviceRESTController implements DeviceAPI {

    private static final String DEVICE_CREATED_PATH = "/app/v1/device/";
    private Logger logger = LoggerFactory.getLogger(DeviceRESTController.class);

    private final DeviceService service;

    @Override
    @Operation(operationId = "getDeviceById", description = "End-point used to get a Device from the application using the Device's ID", summary = "get Device using ID")
    public ResponseEntity<DeviceDTO> getDeviceById(UUID deviceId) {
        logger.info("[START] - DeviceRESTController - getDeviceById");
        var returnedDevice = service.getDeviceById(deviceId);
        logger.info("[FINISH] - DeviceRESTController - getDeviceById");
        return ResponseEntity.ok(new DeviceDTO(returnedDevice));
    }

    @Override
    @Operation(operationId = "createDevice", description = "End-point used to create a Device from the device form, you should send the name and type of the device", summary = "Create new device")
    public ResponseEntity<DeviceDTO> createDevice(DeviceForm createDeviceForm,
                                                  UUID customerId,
                                                  UriComponentsBuilder uriBuilder) {
        logger.info("[START] - DeviceRESTController - createDevice");
        Device createdDevice = service.createDevice(createDeviceForm.toEntity(), customerId);
        URI uri = uriBuilder.path(DEVICE_CREATED_PATH.concat(createdDevice.getId().toString()))
                .buildAndExpand(createdDevice.getId()).toUri();
        logger.info("Device {} created", createdDevice.getId());
        logger.info("[FINISH] - DeviceRESTController - createDevice");
        return ResponseEntity.created(uri).body(new DeviceDTO(createdDevice));
    }

    @Override
    @Operation(operationId = "updateDevice", description = "End-point used to update the Device name and type", summary = "Update device")
    public void updateDevice(DeviceForm deviceForm, UUID deviceId) {
        logger.info("[START] - DeviceRESTController - updateDevice");
        service.updateDevice(deviceForm.toEntity(), deviceId);
        logger.info("[FINISH] - DeviceRESTController - updateDevice");
    }

    @Override
    @Operation(operationId = "deleteDevice", description = "End-point used to Delete an Device from a customer", summary = "Delete a device")
    public void deleteDevice(UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceRESTController - deleteService");
        service.deleteDevice(deviceId, customerId);
        logger.info("[FINISH] - DeviceRESTController - deleteService");
    }

    @Override
    @Operation(operationId = "getTotalMonthlyCostById", description = "End-point used to retrieve the total Device cost, including the services cost", summary = "Get total device cost")
    public ResponseEntity<DeviceTotalMonthlyCostDTO> getTotalMonthlyCostById(UUID deviceId) {
        logger.info("[START] - DeviceRESTController - getTotalMonthlyCostById");
        var returnedDeviceTotalMonthlyCost = service.getDeviceTotalMonthlyCostById(deviceId);
        logger.info("[FINISH] - DeviceRESTController - getTotalMonthlyCostById");
        return ResponseEntity.ok(new DeviceTotalMonthlyCostDTO(returnedDeviceTotalMonthlyCost));
    }
}
