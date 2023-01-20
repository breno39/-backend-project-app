package com.breno39.backendproject.customer.domain;

import com.breno39.backendproject.service.domain.domain.Device;
import com.breno39.backendproject.service.domain.domain.DeviceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CustomerTest {

    @Test
    void mustAddDeviceAndCalculateTotalMonthlyCost() {
        /*var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.WINDOWS_SERVER).build();
        var customer = Customer.builder().id(UUID.randomUUID()).build();

        customer.addDevice(device);

        Assertions.assertEquals(Device.DEVICE_COST, device.getTotalMonthlyCost());
        Assertions.assertTrue(customer.getDevices().contains(device));*/
    }

    @Test
    void mustRemoveDeviceAndCalculateTotalMonthlyCost() {
        /*var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.WINDOWS_SERVER).build();
        var customer = Customer.builder().id(UUID.randomUUID()).build();


        Assertions.assertTrue(device.getServices().isEmpty());
        Assertions.assertNotEquals(0L, device.getTotalMonthlyCost());*/
    }
}