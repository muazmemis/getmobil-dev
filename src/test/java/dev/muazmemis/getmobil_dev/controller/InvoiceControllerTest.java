package dev.muazmemis.getmobil_dev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.muazmemis.getmobil_dev.entity.Invoice;
import dev.muazmemis.getmobil_dev.service.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = InvoiceController.class)
class InvoiceControllerTest {

    private static final String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InvoiceService invoiceService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public InvoiceService invoiceService() {
            return mock(InvoiceService.class);
        }
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // TODO: Fix the test
    @Test
    void findAll() throws Exception {
        // Arrange
//        Invoice invoice = Invoice.builder().id(1L).build();
//        when(invoiceService.findAll()).thenReturn(List.of(invoice));
//
//        // Act & Assert
//        MvcResult mvcResult = mockMvc.perform(get("/api/v1/invoice")
//                        .contentType(CONTENT_TYPE)
//                        .accept(CONTENT_TYPE))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String responseBody = mvcResult.getResponse().getContentAsString();
//        verify(invoiceService).findAll();
//
//        assertThat(objectMapper.writeValueAsString(List.of(invoice)))
//                .isEqualToIgnoringWhitespace(responseBody);
    }

    @Test
    void findById() throws Exception {
        // Arrange
//        Long invoiceId = 1L;
//        Invoice invoice = Invoice.builder().id(invoiceId).build();
//        when(invoiceService.findById(invoiceId)).thenReturn(invoice);
//
//        // Act & Assert
//        MvcResult mvcResult = mockMvc.perform(get("/api/v1/invoice/" + invoiceId)
//                        .accept(CONTENT_TYPE))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String responseBody = mvcResult.getResponse().getContentAsString();
//        verify(invoiceService).findById(invoiceId);
//
//        assertThat(objectMapper.writeValueAsString(invoice))
//                .isEqualToIgnoringWhitespace(responseBody);
    }
}
