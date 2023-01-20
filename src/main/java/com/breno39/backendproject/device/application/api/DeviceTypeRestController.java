package com.breno39.backendproject.device.application.api;

import com.breno39.backendproject.device.application.service.DeviceTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "openApiAuth")
public class DeviceTypeRestController implements DeviceTypeAPI{
    private static final String DEVICE_TYPE_CREATED_PATH = "/app/v1/deviceType/";

    //private final DeviceTypeService service;

    @Override
    public ResponseEntity<DeviceDTO> createDeviceType(DeviceTypeForm createDeviceTypeForm, UriComponentsBuilder uriBuilder) {
        return null;
    }

    @Override
    public ResponseEntity<DeviceDTO> getDeviceTypeIdById(UUID deviceId) {
        return null;
    }

    @Override
    public void deleteDeviceTypeId(UUID deviceId) {

    }
}
