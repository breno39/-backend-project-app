package com.breno39.backendproject.device.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@RestController
public interface DeviceTypeAPI {

    @PostMapping("private/v1/admin/deviceType")
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<DeviceDTO> createDeviceType(@RequestBody @Valid DeviceTypeForm createDeviceTypeForm, UriComponentsBuilder uriBuilder);

    @GetMapping("private/v1/admin/deviceType/{deviceTypeId}")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<DeviceDTO> getDeviceTypeIdById(@NotEmpty @PathVariable("deviceTypeId") UUID deviceId);

    @DeleteMapping("private/v1/admin/deviceType/{deviceTypeId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteDeviceTypeId(@NotEmpty @PathVariable("deviceTypeId") UUID deviceId);
}
