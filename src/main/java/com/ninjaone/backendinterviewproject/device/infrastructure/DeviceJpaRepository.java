package com.ninjaone.backendinterviewproject.device.infrastructure;

import com.ninjaone.backendinterviewproject.device.application.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.service.infrastructure.ServiceSpringDataJPARepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeviceJpaRepository implements DeviceRepository {
    private Logger logger = LoggerFactory.getLogger(DeviceJpaRepository.class);

    private final DeviceSpringDataJPARepository deviceRepository;
    private final ServiceSpringDataJPARepository serviceRepository;

    @Override
    public Device createDevice(Device device) {
        logger.info("[START] - DeviceJpaRepository - createDevice");

        if(!device.getServices().isEmpty()) {
            serviceRepository.saveAllAndFlush(device.getServices());
        }

        Device createdDevice = deviceRepository.save(device);
        logger.info("[FINISH] - DeviceJpaRepository - createDevice");
        return createdDevice;
    }

    @Override
    public void deleteDevice(UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceJpaRepository - deleteDevice");
        deviceRepository.deleteById(deviceId);
        logger.info("[FINISH] - DeviceJpaRepository - deleteDevice");
    }

    @Override
    public Optional<Device> findByIdAndCustomerId(UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceJpaRepository - findByIdAndCustomerId");
        Optional<Device> returnedDevice = deviceRepository.findById(deviceId);
        logger.info("[FINISH] - DeviceJpaRepository - findByIdAndCustomerId");
        return returnedDevice;
    }

    @Override
    public void updateDevice(Device device) {
        logger.info("[START] - DeviceJpaRepository - updateDevice");
        deviceRepository.save(device);
        logger.info("[FINISH] - DeviceJpaRepository - updateDevice");
    }
}
