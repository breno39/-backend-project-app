package com.ninjaone.backendinterviewproject.device.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@RestController
public interface DeviceAPI {
    @GetMapping("private/v1/customer/device/{deviceId}")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<DeviceDTO> getDeviceById(@NotEmpty @PathVariable("deviceId") UUID deviceId);

    @PostMapping("private/v1/customer/{customerId}/device")
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<DeviceDTO> createDevice(@RequestBody @Valid DeviceForm createDeviceForm, @NotEmpty @PathVariable("customerId") UUID customerId, UriComponentsBuilder uriBuilder);

    @PatchMapping("private/v1/customer/device/{deviceId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void updateDevice(@RequestBody DeviceForm deviceForm, @NotEmpty @PathVariable("deviceId") UUID deviceId);

    @DeleteMapping("private/v1/customer/{customerId}/device/{deviceId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteDevice(@NotEmpty @PathVariable("deviceId") UUID deviceId, @NotEmpty @PathVariable("customerId") UUID customerId);

    @GetMapping("private/v1/customer/device/{deviceId}/cost")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<DeviceTotalMonthlyCostDTO> getTotalMonthlyCostById(@NotEmpty @PathVariable("deviceId") UUID deviceId);

}
