package com.breno39.backendproject.service.application.service;

import com.breno39.backendproject.device.application.service.DeviceService;
import com.breno39.backendproject.handler.ApiException;
import com.breno39.backendproject.device.domain.Device;
import com.breno39.backendproject.service.domain.ServiceType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceSpringDataJPAService implements ServiceService{
    private final Logger logger = LoggerFactory.getLogger(ServiceSpringDataJPAService.class);

    private final DeviceService deviceService;

    @Override
    public ServiceType[] getAvailableServices() {
        logger.info("[START] - ServiceSpringDataJPAService - getAvailableServices");
        var availableServiceTypes = ServiceType.values();
        logger.info("[FINISH] - ServiceSpringDataJPAService - getAvailableServices");
        return availableServiceTypes;
    }

    @Override
    public com.breno39.backendproject.service.domain.Service addAvailableServiceToDevice(ServiceType type, UUID deviceId) {
        logger.info("[START] - ServiceSpringDataJPAService - addAvailableServiceToDevice");
        Device returnedDevice = deviceService.getDeviceById(deviceId);
        if(returnedDevice.getServiceByServiceType(type).isEmpty()) {
            var newService = new com.breno39.backendproject.service.domain.Service(type);
            checkServiceCompatibility(returnedDevice, type);
            logger.info("adding service of type {} to Device {}", type, deviceId);
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
        logger.info("removed service of type {} of Device {}", type, deviceId);
        logger.info("[FINISH] - ServiceSpringDataJPAService - removeAvailableServiceFromDevice");
    }

    private void checkServiceCompatibility(Device device, ServiceType type) {
        if(!device.isCompatible(type)) {
            throw ApiException.throwApiException(HttpStatus.BAD_REQUEST, "Service and Device are not compatible");
        }
    }

}
