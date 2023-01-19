package com.breno39.backendproject.customer.application.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.breno39.backendproject.customer.application.service.CustomerService;
import com.breno39.backendproject.customer.domain.Customer;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerRESTController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@ActiveProfiles("test")
class CustomerRESTControllerTest {
    public static final String CUSTOMER_ID = UUID.randomUUID().toString();
    public static final String PRIVATE_CUSTOMER_PATH = new StringBuilder("/private/v1/customer").toString();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    @WithMockUser(value = "spring")
    void mustReturnTotalMonthlyCost() throws Exception {
        var customer = Customer.builder().id(UUID.fromString(CUSTOMER_ID)).build();

        when(customerService.getTotalMonthlyCost(UUID.fromString(CUSTOMER_ID))).thenReturn(customer.getTotalMonthlyCost());

        mockMvc.perform(get(PRIVATE_CUSTOMER_PATH.concat("/").concat(CUSTOMER_ID).concat("/cost")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(new CustomerTotalMonthlyCostDTO(customer.getTotalMonthlyCost()))));
    }
}