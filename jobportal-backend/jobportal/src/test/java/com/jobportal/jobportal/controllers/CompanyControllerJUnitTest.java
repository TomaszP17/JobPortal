package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.company.CompanyResponseDTO;
import com.jobportal.jobportal.services.company.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

/**
 * Annotation @WebMvcTest because we test web layer our application
 */
@WebMvcTest(CompanyController.class)
class CompanyControllerJUnitTest {

    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    void testGetCompanyById() throws Exception {
        Long companyId = 1L;
        CompanyResponseDTO responseDto = new CompanyResponseDTO(
                "Test Company",
                );
        responseDto.setId(companyId);
        responseDto.setName("Test Company");
        // Ustaw inne wymagane pola

        when(companyService.getCompanyById(companyId)).thenReturn(responseDto);

        mockMvc.perform(get("/api/companies/{id}", companyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(companyId.intValue())))
                .andExpect(jsonPath("$.name", is("Test Company")));
    }

}