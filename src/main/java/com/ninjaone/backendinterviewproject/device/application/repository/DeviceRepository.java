package com.ninjaone.backendinterviewproject.device.application.repository;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository {
    Device createDevice(Device device);

    void deleteDevice(UUID deviceId, UUID customerId);

    Optional<Device> findByIdAndCustomerId(UUID deviceId, UUID customerId);

    void updateDevice(Device device);
}
