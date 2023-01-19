package com.breno39.backendinterviewproject.device.application.service;

import com.breno39.backendinterviewproject.device.domain.Device;
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
