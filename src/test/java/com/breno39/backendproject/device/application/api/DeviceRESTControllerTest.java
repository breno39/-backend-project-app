package com.breno39.backendproject.device.application.api;

import com.breno39.backendproject.BackendProjectApplication;
import com.breno39.backendproject.device.application.service.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.breno39.backendproject.device.domain.Device;
import com.breno39.backendproject.device.domain.DeviceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DeviceRESTController.class)
@ContextConfiguration(classes = {BackendProjectApplication.class})
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
class DeviceRESTControllerTest {
    public static final String DEVICE_ID = UUID.randomUUID().toString();
    public static final String CUSTOMER_ID = UUID.randomUUID().toString();
    public static final String PRIVATE_DEVICE_PATH_WITH_CUSTOMER_ID = new StringBuilder("/private/v1/customer/")
            .append(CUSTOMER_ID).append("/device").toString();
    public static final String PRIVATE_DEVICE_PATH_WITHOUT_CUSTOMER_ID = "/private/v1/customer/device";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private DeviceService deviceService;

    @Test
    @WithMockUser(value = "spring")
    void mustGetDeviceById() throws Exception {
        var device = Device.builder().id(UUID.fromString(DEVICE_ID)).systemName("teste").type(DeviceType.MAC).build();

        when(deviceService.getDeviceById(UUID.fromString(DEVICE_ID))).thenReturn(device);

        mockMvc.perform(get(PRIVATE_DEVICE_PATH_WITHOUT_CUSTOMER_ID.concat("/").concat(DEVICE_ID)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(new DeviceDTO(device))));
    }

    @Test
    @WithMockUser(value = "spring")
    void mustUpdateDevice() throws Exception {
        var device = Device.builder().id(UUID.fromString(DEVICE_ID)).systemName("teste").type(DeviceType.MAC).build();

        when(deviceService.updateDevice(device, UUID.fromString(DEVICE_ID))).thenReturn(device);

        mockMvc.perform(patch(PRIVATE_DEVICE_PATH_WITHOUT_CUSTOMER_ID.concat("/").concat(DEVICE_ID))
                        .content(objectMapper.writeValueAsString(new DeviceForm(device.getSystemName(), device.getType())))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(value = "spring")
    void mustDeleteDevice() throws Exception {
        doNothing().when(deviceService).deleteDevice(UUID.fromString(DEVICE_ID), UUID.fromString(CUSTOMER_ID));

        mockMvc.perform(delete(PRIVATE_DEVICE_PATH_WITH_CUSTOMER_ID.concat("/").concat(DEVICE_ID)))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(value = "spring")
    void getTotalMonthlyCostById() throws Exception {
        var device = Device.builder().id(UUID.fromString(DEVICE_ID)).systemName("teste").type(DeviceType.MAC).build();

        when(deviceService.getDeviceTotalMonthlyCostById(UUID.fromString(DEVICE_ID))).thenReturn(device.getTotalMonthlyCost());

        mockMvc.perform(get(PRIVATE_DEVICE_PATH_WITHOUT_CUSTOMER_ID.concat("/").concat(DEVICE_ID).concat("/cost")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(new DeviceTotalMonthlyCostDTO(Device.DEVICE_COST))));
    }
}