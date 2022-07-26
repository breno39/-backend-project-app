package com.ninjaone.backendinterviewproject.device.application.service;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DeviceService {
    Device getDeviceById(UUID deviceId);

    Device createDevice(Device device, UUID customerId);

    Device updateDevice(Device device, UUID deviceId);

    void deleteDevice(UUID deviceId, UUID customerId);

    Long getDeviceTotalMonthlyCostById(UUID deviceId);
}
