package com.ninjaone.backendinterviewproject.device.application.service;

import com.ninjaone.backendinterviewproject.customer.application.service.CustomerService;
import com.ninjaone.backendinterviewproject.customer.domain.Customer;
import com.ninjaone.backendinterviewproject.device.application.repository.DeviceRepository;
import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.device.domain.DeviceType;
import com.ninjaone.backendinterviewproject.handler.ApiException;
import com.ninjaone.backendinterviewproject.service.domain.Service;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceSpringDataJPAServiceTest {
    private static final UUID DEVICE_ID = UUID.randomUUID();
    public static final UUID CUSTOMER_ID = UUID.randomUUID();

    @InjectMocks
    private DeviceSpringDataJPAService deviceSpringDataJPAService;

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private CustomerService customerService;

    @Test
    void mustReturnDeviceById() {
        var device = Device.builder().id(DEVICE_ID).systemName("teste").type(DeviceType.MAC).build();

        when(deviceRepository.findById(DEVICE_ID)).thenReturn(Optional.of(device));

        var returnedDevice = deviceSpringDataJPAService.getDeviceById(DEVICE_ID);

        Assertions.assertEquals(device, returnedDevice);
    }

    @Test
    void mustThrowApiExceptionAndNotReturnDeviceById() {
        when(deviceRepository.findById(DEVICE_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(ApiException.class, () -> deviceSpringDataJPAService.getDeviceById(DEVICE_ID));
    }

    @Test
    void MustCreateDevice() {
        var device = Device.builder().id(DEVICE_ID).systemName("teste").type(DeviceType.MAC).build();
        var customer = Customer.builder().id(CUSTOMER_ID).build();

        when(deviceRepository.createDevice(device)).thenReturn(device);
        when(customerService.getCustomerById(CUSTOMER_ID)).thenReturn(customer);
        doNothing().when(customerService).updateCustomer(customer);

        var returnedDevice = deviceSpringDataJPAService.createDevice(device, CUSTOMER_ID);

        Assertions.assertTrue(customer.getDevices().contains(device));
        Assertions.assertEquals(CUSTOMER_ID, returnedDevice.getCustomer().getId());
    }

    @Test
    void MustThrowApiExceptionAndNotCreateDuplicatedDevice() {
        var device = Device.builder().id(DEVICE_ID).systemName("teste").type(DeviceType.MAC).build();
        var customer = Customer.builder().id(CUSTOMER_ID).build();

        when(deviceRepository.createDevice(device)).thenThrow(ApiException.throwApiException(HttpStatus.BAD_REQUEST, ""));
        when(customerService.getCustomerById(CUSTOMER_ID)).thenReturn(customer);

        Assertions.assertThrows(ApiException.class, () -> deviceSpringDataJPAService.createDevice(device, CUSTOMER_ID));

        Assertions.assertFalse(customer.getDevices().contains(device));
        Assertions.assertNull(device.getCustomer());
    }


    @Test
    void MustUpdateDeviceButNotServices() {
        var deviceToBeUpdated = Device.builder().id(DEVICE_ID).systemName("teste").type(DeviceType.WINDOWS_WORKSTATION).build();
        var device = Device.builder().id(null).systemName("ninja").type(DeviceType.WINDOWS_SERVER).build();
        var service = new Service(ServiceType.PSA);

        deviceToBeUpdated.addService(service);

        when(deviceRepository.findById(DEVICE_ID)).thenReturn(Optional.of(deviceToBeUpdated));
        when(deviceRepository.updateDevice(deviceToBeUpdated)).thenReturn(deviceToBeUpdated);

        var returnedDevice = deviceSpringDataJPAService.updateDevice(device, DEVICE_ID);

        Assertions.assertEquals(device.getSystemName(), returnedDevice.getSystemName());
        Assertions.assertEquals(device.getType(), returnedDevice.getType());
        Assertions.assertFalse(returnedDevice.getServices().isEmpty());
    }

    @Test
    void MustUpdateDeviceAndServices() {
        var deviceToBeUpdated = Device.builder().id(DEVICE_ID).systemName("teste").type(DeviceType.WINDOWS_WORKSTATION).build();
        var device = Device.builder().id(null).systemName("ninja").type(DeviceType.WINDOWS_SERVER).build();
        var service = new Service(ServiceType.PSA);

        device.addService(service);

        when(deviceRepository.findById(DEVICE_ID)).thenReturn(Optional.of(deviceToBeUpdated));
        when(deviceRepository.updateDevice(deviceToBeUpdated)).thenReturn(deviceToBeUpdated);

        var returnedDevice = deviceSpringDataJPAService.updateDevice(device, DEVICE_ID);

        Assertions.assertEquals(device.getSystemName(), returnedDevice.getSystemName());
        Assertions.assertEquals(device.getType(), returnedDevice.getType());
        Assertions.assertFalse(returnedDevice.getServices().isEmpty());
    }

    @Test
    void mustDeleteDeviceById() {
        var customer = Customer.builder().id(CUSTOMER_ID).build();
        var device = Device.builder()
                .id(DEVICE_ID)
                .systemName("teste")
                .type(DeviceType.MAC)
                .customer(customer)
                .build();
        customer.addDevice(device);

        when(deviceRepository.findById(DEVICE_ID)).thenReturn(Optional.of(device));
        when(customerService.getCustomerById(CUSTOMER_ID)).thenReturn(customer);
        doNothing().when(customerService).updateCustomer(customer);

        deviceSpringDataJPAService.deleteDevice(DEVICE_ID, CUSTOMER_ID);

        Assertions.assertTrue(customer.getDevices().isEmpty());
        Assertions.assertNull(device.getCustomer());
    }

    @Test
    void mustReturnDeviceTotalMonthlyCostById() {
        var device = Device.builder().id(DEVICE_ID).systemName("ninja").type(DeviceType.WINDOWS_SERVER).build();

        when(deviceRepository.findTotalMonthlyCostById(DEVICE_ID)).thenReturn(Optional.of(device.getTotalMonthlyCost()));

        Long returnedCost = deviceSpringDataJPAService.getDeviceTotalMonthlyCostById(DEVICE_ID);

        Assertions.assertEquals(Device.DEVICE_COST, returnedCost);
    }

    @Test
    void mustThrowExceptionAndNotReturnDeviceTotalMonthlyCostById() {
        var device = Device.builder().id(DEVICE_ID).systemName("ninja").type(DeviceType.WINDOWS_SERVER).build();

        when(deviceRepository.findTotalMonthlyCostById(DEVICE_ID))
                .thenThrow(ApiException.throwApiException(HttpStatus.NOT_FOUND, ""));

        Assertions.assertThrows(ApiException.class,
                () -> deviceSpringDataJPAService.getDeviceTotalMonthlyCostById(DEVICE_ID));
    }
}