package com.breno39.backendinterviewproject.device.domain;

import com.breno39.backendinterviewproject.service.domain.Service;
import com.breno39.backendinterviewproject.service.domain.ServiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

class DeviceTest {

    @Test
    void mustAddCompatibleMacServiceAndCalculateTotalMonthlyCost() {
        var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.MAC).build();
        var type = ServiceType.MAC_ANTIVIRUS;
        var service = new Service(type);
        var totalMonthlyCostAfterAdd = type.getCost() + Device.DEVICE_COST;

        device.addService(service);

        Assertions.assertFalse(device.getServices().isEmpty());
        Assertions.assertEquals(totalMonthlyCostAfterAdd, device.getTotalMonthlyCost());
        Assertions.assertEquals(device, service.getDevice());
    }

    @Test
    void mustNotAddIncompatibleMacServiceAndMaintainTotalMonthlyCost() {
        var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.MAC).build();
        var type = ServiceType.WINDOWS_ANTIVIRUS;
        var service = new Service(type);
        var totalMonthlyCostIfAdded = type.getCost() + Device.DEVICE_COST;

        device.addService(service);

        Assertions.assertTrue(device.getServices().isEmpty());
        Assertions.assertNotEquals(totalMonthlyCostIfAdded, device.getTotalMonthlyCost());
        Assertions.assertNull(service.getDevice());
    }

    @Test
    void mustAddAnyServiceAndCalculateTotalMonthlyCost() {
        var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.MAC).build();
        var type = ServiceType.BACKUP;
        var service = new Service(type);
        var totalMonthlyCostAfterAdd = type.getCost() + Device.DEVICE_COST;

        device.addService(service);

        Assertions.assertFalse(device.getServices().isEmpty());
        Assertions.assertEquals(totalMonthlyCostAfterAdd, device.getTotalMonthlyCost());
        Assertions.assertEquals(device, service.getDevice());
    }

    //Can't use Set.of because it returns unmodifiable set
    @Test
    void mustRemoveServiceAndCalculateTotalMonthlyCost() {
        var type = ServiceType.BACKUP;
        var service = new Service(type);
        var services = new HashSet<Service>();
        services.add(service);
        var device = Device.builder()
                .id(UUID.randomUUID()).systemName("teste").type(DeviceType.MAC).services(services).build();

        device.removeService(service);

        Assertions.assertTrue(device.getServices().isEmpty());
        Assertions.assertEquals(Device.DEVICE_COST, device.getTotalMonthlyCost());
        Assertions.assertNull(service.getDevice());
    }

    @Test
    void mustBeCompatibleWithCompatibleServiceType() {
        var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.MAC).build();
        var typeBackup = ServiceType.BACKUP;
        var typeMac = ServiceType.MAC_ANTIVIRUS;

        var returnedCompatibleBackup = device.isCompatible(typeBackup);
        var returnedCompatibleMac = device.isCompatible(typeMac);

        Assertions.assertTrue(returnedCompatibleBackup);
        Assertions.assertTrue(returnedCompatibleMac);
    }

    @Test
    void mustBeIncompatibleWithIncompatibleServiceType() {
        var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.MAC).build();
        var typeBackup = ServiceType.BACKUP;
        var typeWindows = ServiceType.WINDOWS_ANTIVIRUS;

        var returnedCompatibleBackup = device.isCompatible(typeBackup);
        var returnedIncompatibleMac = device.isCompatible(typeWindows);

        Assertions.assertTrue(returnedCompatibleBackup);
        Assertions.assertFalse(returnedIncompatibleMac);
    }

    @Test
    void getServiceByServiceType() {
        var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.WINDOWS_SERVER).build();
        var serviceBackup = new Service(ServiceType.BACKUP);
        var serviceWindows = new Service(ServiceType.WINDOWS_ANTIVIRUS);
        device.addService(serviceBackup);
        device.addService(serviceWindows);

        var returnedService = device.getServiceByServiceType(ServiceType.WINDOWS_ANTIVIRUS);

        Assertions.assertFalse(returnedService.isEmpty());
        Assertions.assertEquals(serviceWindows, returnedService.get());
    }
}