package com.ninjaone.backendinterviewproject.device.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@RestController
@RequestMapping("customer/{customerId}/device")
public interface DeviceAPI {
    @GetMapping("/{deviceId}")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<DeviceDTO> getDeviceById(@NotEmpty @PathVariable("deviceId") UUID deviceId, @NotEmpty @PathVariable("customerId") UUID customerId);

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<DeviceDTO> createDevice(@RequestBody @Valid CreateDeviceForm createDeviceForm, @NotEmpty @PathVariable("customerId") UUID customerId, UriComponentsBuilder uriBuilder);

    @PutMapping("/{deviceId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void updateDevice(@RequestBody DeviceForm deviceForm, @NotEmpty @PathVariable("deviceId") UUID deviceId, @NotEmpty @PathVariable("customerId") UUID customerId);

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteDevice(@NotEmpty @PathVariable("deviceId") UUID deviceId, @NotEmpty @PathVariable("customerId") UUID customerId);

}
