package com.ninjaone.backendinterviewproject.device.application.api;

import com.ninjaone.backendinterviewproject.device.application.service.DeviceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
public class DeviceRESTController implements DeviceAPI {

    private static final String DEVICE_CREATED_PATH = "/app/v1/device/";
    private Logger logger = LoggerFactory.getLogger(DeviceRESTController.class);

    private final DeviceService service;

    @Override
    public ResponseEntity<DeviceDTO> getDeviceById(UUID deviceId) {
        logger.info("[START] - DeviceRESTController - getDeviceById");
        var returnedDevice = service.getDeviceById(deviceId);
        logger.info("[FINISH] - DeviceRESTController - getDeviceById");
        return ResponseEntity.ok(new DeviceDTO(returnedDevice));
    }

    @Override
    public ResponseEntity<DeviceDTO> createDevice(DeviceForm createDeviceForm,
                                                  UUID customerId,
                                                  UriComponentsBuilder uriBuilder) {
        logger.info("[START] - DeviceRESTController - createDevice");
        var createdDevice = service.createDevice(createDeviceForm.toEntity(), customerId);
        URI uri = uriBuilder.path(DEVICE_CREATED_PATH.concat(createdDevice.getId().toString()))
                .buildAndExpand(createdDevice.getId()).toUri();
        logger.info("Device {} created", createdDevice.getId());
        logger.info("[FINISH] - DeviceRESTController - createDevice");
        return ResponseEntity.created(uri).body(new DeviceDTO(createdDevice));
    }

    @Override
    public void updateDevice(DeviceForm deviceForm, UUID deviceId) {
        logger.info("[START] - DeviceRESTController - updateDevice");
        service.updateDevice(deviceForm.toEntity(), deviceId);
        logger.info("[FINISH] - DeviceRESTController - updateDevice");
    }

    @Override
    public void deleteDevice(UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceRESTController - deleteService");
        service.deleteDevice(deviceId, customerId);
        logger.info("[FINISH] - DeviceRESTController - deleteService");
    }

    @Override
    public ResponseEntity<DeviceTotalMonthlyCostDTO> getTotalMonthlyCostById(UUID deviceId) {
        logger.info("[START] - DeviceRESTController - getTotalMonthlyCostById");
        var returnedDeviceTotalMonthlyCost = service.getDeviceTotalMonthlyCostById(deviceId);
        logger.info("[FINISH] - DeviceRESTController - getTotalMonthlyCostById");
        return ResponseEntity.ok(new DeviceTotalMonthlyCostDTO(returnedDeviceTotalMonthlyCost));
    }
}
