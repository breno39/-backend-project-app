package com.ninjaone.backendinterviewproject.service.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service")
public interface ServiceAPI {
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Object> getAvailableServices();

    @PostMapping("add")
    @ResponseStatus(value = HttpStatus.OK)
    public void addAvailableServiceToDevice();

    @GetMapping("remove")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeAvailableServiceFromDevice();

}
