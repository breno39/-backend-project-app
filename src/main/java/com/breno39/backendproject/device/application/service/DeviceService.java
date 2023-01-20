package com.breno39.backendproject.device.application.service;

import com.breno39.backendproject.service.domain.domain.Device;
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
