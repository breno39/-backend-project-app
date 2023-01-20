/*
package com.breno39.backendproject.service.application.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.breno39.backendproject.service.application.service.ServiceService;
import com.breno39.backendproject.service.domain.ServiceType;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ServiceRESTController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
class ServiceRESTControllerTest {
    public static final String DEVICE_ID = UUID.randomUUID().toString();
    public static final String PRIVATE_SERVICE_PATH = new StringBuilder("/private/v1/customer/device/")
            .append(DEVICE_ID).append("/service").toString();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ServiceService serviceService;

    @Test
    void getAvailableServices() throws Exception {
        when(serviceService.getAvailableServices()).thenReturn(ServiceType.values());

        mockMvc.perform(get("/public/v1/customer/device/service"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(ServiceType.values())));
    }

    @Test
    @WithMockUser(value = "spring")
    void addAvailableServiceToDevice() throws Exception {
        var type = ServiceType.WINDOWS_ANTIVIRUS;
        var serviceForm = new ServiceForm(type);
        var returnedService = serviceForm.toEntity();
        returnedService.setId(UUID.randomUUID());

        when(serviceService.addAvailableServiceToDevice(type, UUID.fromString(DEVICE_ID)))
                .thenReturn(returnedService);

        mockMvc.perform(post(PRIVATE_SERVICE_PATH)
                        .content(objectMapper.writeValueAsString(serviceForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(value = "spring")
    void removeAvailableServiceFromDevice() throws Exception {
        var type = ServiceType.WINDOWS_ANTIVIRUS;
        var serviceForm = new ServiceForm(type);

        doNothing().when(serviceService).removeAvailableServiceFromDevice(type, UUID.fromString(DEVICE_ID));

        mockMvc.perform(delete(PRIVATE_SERVICE_PATH)
                        .content(objectMapper.writeValueAsString(serviceForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}*/
