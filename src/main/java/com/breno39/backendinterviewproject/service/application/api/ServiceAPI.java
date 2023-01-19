package com.breno39.backendinterviewproject.service.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@RestController
public interface ServiceAPI {
    @GetMapping("public/v1/customer/device/service")
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<Object> getAvailableServices();

    @PostMapping("private/v1/customer/device/{deviceId}/service")
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<ServiceDTO> addAvailableServiceToDevice(@RequestBody @Valid ServiceForm serviceForm,
                                                           @NotEmpty @PathVariable("deviceId") UUID deviceId,
                                                           UriComponentsBuilder uriBuilder);

    @DeleteMapping("private/v1/customer/device/{deviceId}/service")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void removeAvailableServiceFromDevice(@RequestBody @Valid ServiceForm serviceForm,
                                          @NotEmpty @PathVariable("deviceId") UUID deviceId);

}
