package com.ninjaone.backendinterviewproject.service.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@RestController
@RequestMapping("private/v1/customer/device/{deviceId}/service")
public interface ServiceAPI {
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    ResponseEntity<Object> getAvailableServices();

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    ResponseEntity<ServiceDTO> addAvailableServiceToDevice(@RequestBody @Valid ServiceForm serviceForm,
                                                           @NotEmpty @PathVariable("deviceId") UUID deviceId,
                                                           UriComponentsBuilder uriBuilder);

    @DeleteMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void removeAvailableServiceFromDevice(@RequestParam @Valid ServiceForm serviceForm,
                                          @NotEmpty @PathVariable("deviceId") UUID deviceId);

}
