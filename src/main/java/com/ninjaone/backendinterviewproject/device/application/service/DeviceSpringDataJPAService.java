package com.ninjaone.backendinterviewproject.device.application.service;

import com.ninjaone.backendinterviewproject.customer.application.service.CustomerService;
import com.ninjaone.backendinterviewproject.customer.domain.Customer;
import com.ninjaone.backendinterviewproject.device.application.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.handler.ApiException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceSpringDataJPAService implements DeviceService {
    private final Logger logger = LoggerFactory.getLogger(DeviceSpringDataJPAService.class);

    private final DeviceRepository deviceRepository;
    private final CustomerService customerService;

    @Override
    public Device getDeviceById(UUID deviceId) {
        logger.info("[START] - DeviceSpringDataJPAService - getDeviceById");
        Device returnedDevice = getDeviceByIdOrThrow(deviceId);
        logger.info("[FINISH] - DeviceSpringDataJPAService - getDeviceById");
        return returnedDevice;
    }

    @Override
    public Device createDevice(Device device, UUID customerId) {
        logger.info("[START] - DeviceSpringDataJPAService - createDevice");
        try {
            Customer returnedCustomer = getCustomerById(customerId);
            Device createdDevice = deviceRepository.createDevice(device);
            returnedCustomer.addDevice(createdDevice);
            customerService.updateCustomer(returnedCustomer);
            logger.info("[FINISH] - DeviceSpringDataJPAService - createDevice");
            return createdDevice;
        } catch (DataIntegrityViolationException ex) {
            throw ApiException.throwApiException(HttpStatus.BAD_REQUEST, "Device already exists");
        }
    }

    @Override
    public Device updateDevice(Device device, UUID deviceId) {
        logger.info("[START] - DeviceSpringDataJPAService - updateDevice");
        Device returnedDevice = getDeviceByIdAndCustomerId(deviceId);
        returnedDevice.setType(device.getType());
        returnedDevice.setSystemName(device.getSystemName());
        if(!device.getServices().isEmpty()) {
            returnedDevice.setServices(device.getServices());
        }
        Device updatedDevice = deviceRepository.updateDevice(returnedDevice);
        logger.info("[FINISH] - DeviceSpringDataJPAService - updateDevice");
        return updatedDevice;
    }

    @Override
    public void deleteDevice(UUID deviceId, UUID customerId) {
        logger.info("[START] - DeviceSpringDataJPAService - deleteDevice");
        Customer returnedCustomer = getCustomerById(customerId);
        Device returnedDevice = getDeviceByIdOrThrow(deviceId);
        returnedCustomer.removeDevice(returnedDevice);
        customerService.updateCustomer(returnedCustomer);
        logger.info("[FINISH] - DeviceSpringDataJPAService - deleteDevice");
    }

    //TODO: Refactore this! bring the property direct from repo instead of hole object
    @Override
    public Long getDeviceTotalMonthlyCostById(UUID deviceId) {
        logger.info("[START] - DeviceSpringDataJPAService - getDeviceTotalMonthlyCostById");
        Device returnedDevice = getDeviceByIdAndCustomerId(deviceId);
        logger.info("[FINISH] - DeviceSpringDataJPAService - getDeviceTotalMonthlyCostById");
        return returnedDevice.getTotalMonthlyCost();
    }

    private Device getDeviceByIdAndCustomerId(UUID deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> ApiException.throwApiException(HttpStatus.NOT_FOUND, "Device not found"));
    }

    private Customer getCustomerById(UUID customerId) {
        Customer returnedCustomer = customerService.getCustomerById(customerId)
                .orElseThrow(() -> ApiException.throwApiException(HttpStatus.NOT_FOUND, "customer not found"));
        return returnedCustomer;
    }


    private Device getDeviceByIdOrThrow(UUID deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> ApiException.throwApiException(HttpStatus.NOT_FOUND, "device not found"));
    }
}
