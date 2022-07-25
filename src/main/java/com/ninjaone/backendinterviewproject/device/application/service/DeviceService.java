package com.ninjaone.backendinterviewproject.device.application.service;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DeviceService {
    Device getDeviceById(UUID deviceId, UUID customerId);

    Device createDevice(Device device);

    void updateDevice(Device device, UUID deviceId, UUID customerId);

    void deleteDevice(UUID deviceId, UUID customerId);
}
