package com.ninjaone.backendinterviewproject.device.infrastructure;

import com.ninjaone.backendinterviewproject.device.application.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.device.domain.Device;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeviceJpaRepository implements DeviceRepository {
    private final Logger logger = LoggerFactory.getLogger(DeviceJpaRepository.class);

    private final DeviceSpringDataJPARepository deviceRepository;

    @Override
    public Device createDevice(Device device) {
        logger.info("[START] - DeviceJpaRepository - createDevice");
        Device createdDevice = deviceRepository.saveAndFlush(device);
        logger.info("[FINISH] - DeviceJpaRepository - createDevice");
        return createdDevice;
    }

    @Override
    public Optional<Device> findById(UUID deviceId) {
        logger.info("[START] - DeviceJpaRepository - findByIdAndCustomerId");
        Optional<Device> returnedDevice = deviceRepository.findById(deviceId);
        logger.info("[FINISH] - DeviceJpaRepository - findByIdAndCustomerId");
        return returnedDevice;
    }

    @Override
    public Device updateDevice(Device device) {
        logger.info("[START] - DeviceJpaRepository - updateDevice");
        deviceRepository.save(device);
        logger.info("[FINISH] - DeviceJpaRepository - updateDevice");
        return device;
    }
}
