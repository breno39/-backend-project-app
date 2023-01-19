package com.breno39.backendproject.device.application.repository;

import com.breno39.backendproject.device.domain.Device;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository {
    Device createDevice(Device device);

    Optional<Device> findById(UUID deviceId);

    Device updateDevice(Device device);

    Optional<Long> findTotalMonthlyCostById(UUID deviceId);
}
