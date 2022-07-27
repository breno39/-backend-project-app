package com.ninjaone.backendinterviewproject.customer.domain;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.device.domain.DeviceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void mustAddDeviceAndCalculateTotalMonthlyCost() {
        var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.WINDOWS_SERVER).build();
        var customer = Customer.builder().id(UUID.randomUUID()).build();

        customer.addDevice(device);

        Assertions.assertEquals(Device.DEVICE_COST, device.getTotalMonthlyCost());
        Assertions.assertTrue(customer.getDevices().contains(device));
    }

    @Test
    void mustRemoveDeviceAndCalculateTotalMonthlyCost() {
        var device = Device.builder().id(UUID.randomUUID()).systemName("teste").type(DeviceType.WINDOWS_SERVER).build();
        var customer = Customer.builder().id(UUID.randomUUID()).build();


        Assertions.assertTrue(device.getServices().isEmpty());
        Assertions.assertNotEquals(0L, device.getTotalMonthlyCost());
    }
}