package com.breno39.backendproject.device.application.service;

import com.breno39.backendproject.service.domain.domain.DeviceType;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DeviceTypeService {

    DeviceType createDevice(DeviceType deviceType);

    DeviceType getDeviceTypeIdById(UUID id);

    void deleteDeviceType(UUID id);
}
