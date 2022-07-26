package com.ninjaone.backendinterviewproject.device.application.repository;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository {
    Device createDevice(Device device);

    Optional<Device> findById(UUID deviceId);

    Device updateDevice(Device device);

    Optional<Long> findTotalMonthlyCostById(UUID deviceId);
}
