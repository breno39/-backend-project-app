package com.ninjaone.backendinterviewproject.service.application.service;

import com.ninjaone.backendinterviewproject.device.application.service.DeviceService;
import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.handler.ApiException;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceSpringDataJPAService implements ServiceService{
    private final Logger logger = LoggerFactory.getLogger(ServiceSpringDataJPAService.class);

    private final DeviceService deviceService;

    @Override
    public Set<ServiceType> getAvailableServices() {
        logger.info("[START] - ServiceSpringDataJPAService - getAvailableServices");
        var availableServiceTypes = Arrays.stream(ServiceType.values());
        logger.info("[FINISH] - ServiceSpringDataJPAService - getAvailableServices");
        return availableServiceTypes.collect(Collectors.toSet());
    }

    @Override
    public com.ninjaone.backendinterviewproject.service.domain.Service addAvailableServiceToDevice(ServiceType type, UUID deviceId) {
        logger.info("[START] - ServiceSpringDataJPAService - addAvailableServiceToDevice");
        Device returnedDevice = deviceService.getDeviceById(deviceId);
        if(returnedDevice.getServiceByServiceType(type).isEmpty()) {
            var newService = new com.ninjaone.backendinterviewproject.service.domain.Service(type);
            returnedDevice.addService(newService);
            returnedDevice = deviceService.updateDevice(returnedDevice, deviceId);
            logger.info("[FINISH] - ServiceSpringDataJPAService - addAvailableServiceToDevice");
            return returnedDevice.getServiceByServiceType(type).get();
        } else {
            throw ApiException.throwApiException(HttpStatus.BAD_REQUEST, "Service already exists in Device");
        }
    }

    @Override
    public void removeAvailableServiceFromDevice(ServiceType type, UUID deviceId) {
        logger.info("[START] - ServiceSpringDataJPAService - removeAvailableServiceFromDevice");
        Device returnedDevice = deviceService.getDeviceById(deviceId);
        var returnedService = returnedDevice.getServiceByServiceType(type)
                .orElseThrow(() -> ApiException.throwApiException(HttpStatus.BAD_REQUEST, "Service do not exists in Device"));
        returnedDevice.removeService(returnedService);
        deviceService.updateDevice(returnedDevice, deviceId);
        logger.info("[FINISH] - ServiceSpringDataJPAService - removeAvailableServiceFromDevice");
    }


}
