package com.ninjaone.backendinterviewproject.device.application.service;

import com.ninjaone.backendinterviewproject.device.application.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.handler.ApiException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceSpringDataJPAService implements DeviceService {
    private Logger logger = LoggerFactory.getLogger(DeviceSpringDataJPAService.class);

    private final DeviceRepository deviceRepository;

    @Override
    public Device getDeviceById(UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceSpringDataJPAService - getDeviceById");
        Device returnedDevice = getDeviceByIdAndCustomerId(deviceId, customerId);
        logger.info("[FINISH] - DeviceSpringDataJPAService - getDeviceById");
        return returnedDevice;
    }

    @Override
    public Device createDevice(Device device) {
        logger.info("[START] - DeviceSpringDataJPAService - createDevice");
        Device createdDevice = deviceRepository.createDevice(device);
        logger.info("[FINISH] - DeviceSpringDataJPAService - createDevice");
        return createdDevice;
    }

    @Override
    public void updateDevice(Device device, UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceSpringDataJPAService - updateDevice");
        Device returnedDevice = getDeviceByIdAndCustomerId(deviceId, customerId);
        returnedDevice.setType(device.getType());
        returnedDevice.setSystemName(device.getSystemName());
        if(!device.getServices().isEmpty()) {
            returnedDevice.setServices(device.getServices());
        }
        deviceRepository.updateDevice(returnedDevice);
        logger.info("[FINISH] - DeviceSpringDataJPAService - updateDevice");
    }

    @Override
    public void deleteDevice(UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceSpringDataJPAService - deleteDevice");
        deviceRepository.deleteDevice(deviceId, customerId);
        logger.info("[FINISH] - DeviceSpringDataJPAService - deleteDevice");
    }

    private Device getDeviceByIdAndCustomerId(UUID deviceId, UUID customerId) {
        return deviceRepository.findByIdAndCustomerId(deviceId, customerId)
                .orElseThrow(() -> ApiException.throwApiException(HttpStatus.NOT_FOUND, "Device not found"));
    }
}
