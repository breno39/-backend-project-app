/*
package com.breno39.backendproject.service.application.service;

import com.breno39.backendproject.device.application.service.DeviceService;
import com.breno39.backendproject.service.domain.domain.Device;
import com.breno39.backendproject.service.domain.domain.DeviceType;
import com.breno39.backendproject.handler.ApiException;
import com.breno39.backendproject.service.application.repository.ServiceRepository;
import com.breno39.backendproject.service.domain.Service;
import com.breno39.backendproject.service.domain.ServiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ServiceSpringDataJPAServiceTest {
    private static final UUID DEVICE_ID = UUID.randomUUID();
    public static final UUID SERVICE_ID = UUID.randomUUID();

    @InjectMocks
    private ServiceSpringDataJPAService serviceSpringDataJPAService;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private DeviceService deviceService;

    @Test
    void mustAddNewAvailableServiceToExistingDevice() {
        var type = ServiceType.MAC_ANTIVIRUS;
        var device = Device.builder().id(DEVICE_ID).systemName("teste").type(DeviceType.MAC).build();
        var expectedTotalMonthlyCost = Device.DEVICE_COST + type.getCost();

        Mockito.when(deviceService.getDeviceById(DEVICE_ID)).thenReturn(device);
        Mockito.when(deviceService.updateDevice(device, DEVICE_ID)).thenReturn(device);

        serviceSpringDataJPAService.addAvailableServiceToDevice(type, device.getId());

        Assertions.assertEquals(1, device.getServices().size());
        Assertions.assertEquals(expectedTotalMonthlyCost, device.getTotalMonthlyCost());
    }

    @Test
    void mustNotAddDuplicatedServiceToDeviceAndThrowApiException() {
        var type = ServiceType.MAC_ANTIVIRUS;
        var existingService = new Service(type);
        var device = Device.builder().id(DEVICE_ID).systemName("teste").type(DeviceType.MAC).build();
        var expectedTotalMonthlyCost = Device.DEVICE_COST + type.getCost();
        existingService.setId(UUID.randomUUID());
        device.addService(existingService);

        Mockito.when(deviceService.getDeviceById(DEVICE_ID)).thenReturn(device);

        Assertions.assertThrows(ApiException.class,
                () -> serviceSpringDataJPAService.addAvailableServiceToDevice(type, device.getId()));

        Assertions.assertEquals(1, device.getServices().size());
        Assertions.assertEquals(expectedTotalMonthlyCost, device.getTotalMonthlyCost());
    }

    @Test
    void mustNotAddIncompatibleServiceToDeviceAndThrowApiException() {
        var type = ServiceType.WINDOWS_ANTIVIRUS;
        var device = Device.builder().id(DEVICE_ID).systemName("teste").type(DeviceType.MAC).build();

        Mockito.when(deviceService.getDeviceById(DEVICE_ID)).thenReturn(device);

        Assertions.assertThrows(ApiException.class,
                () -> serviceSpringDataJPAService.addAvailableServiceToDevice(type, device.getId()));

        Assertions.assertEquals(Collections.emptySet(), device.getServices());
        Assertions.assertEquals(Device.DEVICE_COST, device.getTotalMonthlyCost());
    }

    @Test
    void mustRemoveAvailableServiceFromDevice() {
        var type = ServiceType.MAC_ANTIVIRUS;
        var existingService = new Service(type);
        var device = Device.builder().id(DEVICE_ID).systemName("teste").type(DeviceType.MAC).build();
        existingService.setId(SERVICE_ID);
        existingService.setDevice(device);
        device.addService(existingService);

        Mockito.when(deviceService.getDeviceById(DEVICE_ID)).thenReturn(device);
        Mockito.when(deviceService.updateDevice(device, DEVICE_ID)).thenReturn(device);

        serviceSpringDataJPAService.removeAvailableServiceFromDevice(type, device.getId());

        Assertions.assertEquals(0, device.getServices().size());
        Assertions.assertEquals(Device.DEVICE_COST, device.getTotalMonthlyCost());
        Assertions.assertNull(existingService.getDevice());
    }
}*/
