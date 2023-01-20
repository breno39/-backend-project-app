package com.breno39.backendproject.device.application.api;

import com.breno39.backendproject.device.application.service.DeviceService;
import com.breno39.backendproject.service.domain.domain.Device;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "openApiAuth")
@Tag(name = "Devices API", description = "Contains the CRUD of the Service domain, can be use to get the total monthly cost of a single device, also contains endpoints to add/remove Services")
public class DeviceRestController implements DeviceAPI {

    private static final String DEVICE_CREATED_PATH = "/app/v1/device/";

    private final DeviceService service;

    @Override
    @Operation(operationId = "getDeviceById", description = "End-point used to get a Device from the application using the Device's ID", summary = "get Device using ID")
    public ResponseEntity<DeviceDTO> getDeviceById(UUID deviceId) {
        log.info("[START] - DeviceRESTController - getDeviceById");
        var returnedDevice = service.getDeviceById(deviceId);
        log.info("[FINISH] - DeviceRESTController - getDeviceById");
        return ResponseEntity.ok(new DeviceDTO(returnedDevice));
    }

    @Override
    @Operation(operationId = "createDevice", description = "End-point used to create a Device from the device form, you should send the name and type of the device", summary = "Create new device")
    public ResponseEntity<DeviceDTO> createDevice(DeviceForm createDeviceForm,
                                                  UUID customerId,
                                                  UriComponentsBuilder uriBuilder) {
        log.info("[START] - DeviceRESTController - createDevice");
        Device createdDevice = service.createDevice(createDeviceForm.toEntity(), customerId);
        URI uri = uriBuilder.path(DEVICE_CREATED_PATH.concat(createdDevice.getId().toString()))
                .buildAndExpand(createdDevice.getId()).toUri();
        log.info("Device {} created", createdDevice.getId());
        log.info("[FINISH] - DeviceRESTController - createDevice");
        return ResponseEntity.created(uri).body(new DeviceDTO(createdDevice));
    }

    @Override
    @Operation(operationId = "updateDevice", description = "End-point used to update the Device name and type", summary = "Update device")
    public void updateDevice(DeviceForm deviceForm, UUID deviceId) {
        log.info("[START] - DeviceRESTController - updateDevice");
        service.updateDevice(deviceForm.toEntity(), deviceId);
        log.info("[FINISH] - DeviceRESTController - updateDevice");
    }

    @Override
    @Operation(operationId = "deleteDevice", description = "End-point used to Delete an Device from a customer", summary = "Delete a device")
    public void deleteDevice(UUID deviceId, UUID customerId) {
        log.info("[START] - DeviceRESTController - deleteService");
        service.deleteDevice(deviceId, customerId);
        log.info("[FINISH] - DeviceRESTController - deleteService");
    }

    @Override
    @Operation(operationId = "getTotalMonthlyCostById", description = "End-point used to retrieve the total Device cost, including the services cost", summary = "Get total device cost")
    public ResponseEntity<DeviceTotalMonthlyCostDTO> getTotalMonthlyCostById(UUID deviceId) {
        log.info("[START] - DeviceRESTController - getTotalMonthlyCostById");
        var returnedDeviceTotalMonthlyCost = service.getDeviceTotalMonthlyCostById(deviceId);
        log.info("[FINISH] - DeviceRESTController - getTotalMonthlyCostById");
        return ResponseEntity.ok(new DeviceTotalMonthlyCostDTO(returnedDeviceTotalMonthlyCost));
    }
}
