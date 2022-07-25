package com.ninjaone.backendinterviewproject.device.application.api;

import com.ninjaone.backendinterviewproject.device.application.service.DeviceService;
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
public class DeviceRESTController implements DeviceAPI {

    private static final StringBuilder DEVICE_CREATED_PATH = new StringBuilder("ninjaone/app/v1/device/");
    private Logger logger = LoggerFactory.getLogger(DeviceRESTController.class);

    private final DeviceService service;

    @Override
    public ResponseEntity<DeviceDTO> getDeviceById(UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceRESTController - getDeviceById");
        logger.info(customerId.toString());
        var returnedDevice = service.getDeviceById(deviceId, customerId);
        logger.info("[FINISH] - DeviceRESTController - getDeviceById");
        return ResponseEntity.ok(new DeviceDTO(returnedDevice));
    }

    @Override
    public ResponseEntity<DeviceDTO> createDevice(CreateDeviceForm createDeviceForm, UriComponentsBuilder uriBuilder) {
        logger.info("[START] - DeviceRESTController - createDevice");
        var createdDevice = service.createDevice(createDeviceForm.toEntity());
        URI uri = uriBuilder.path(DEVICE_CREATED_PATH.append(createdDevice.getId().toString()).toString())
                .buildAndExpand(createdDevice.getId()).toUri();
        logger.info("[FINISH] - DeviceRESTController - createDevice");
        return ResponseEntity.created(uri).body(new DeviceDTO(createdDevice));
    }

    @Override
    public void updateDevice(DeviceForm deviceForm, UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceRESTController - updateDevice");
        service.updateDevice(deviceForm.toEntity(), deviceId, customerId);
        logger.info("[FINISH] - DeviceRESTController - updateDevice");
    }

    @Override
    public void deleteDevice(UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceRESTController - deleteService");
        service.deleteDevice(deviceId, customerId);
        logger.info("[FINISH] - DeviceRESTController - deleteService");
    }
}
