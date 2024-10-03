package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.company.CompanyResponseDTO;
import com.jobportal.jobportal.services.company.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

class CompanyControllerJUnitTest {

    private MockMvc mockMvc;
    private CompanyService companyService;
    private CompanyController companyController;

    @BeforeEach
    void setUp() {
        companyService = Mockito.mock(CompanyService.class);
        companyController = new CompanyController(companyService);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    void testGetCompanyById() throws Exception {
        Long companyId = 1L;
        CompanyResponseDTO responseDto = new CompanyResponseDTO(
                "Cweluch",
                "1234567890",
                "t@gmail.com",
                "https://www.linkedin.com/company/test-company"
        );

        Mockito.when(companyService.getCompanyById(companyId)).thenReturn(responseDto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/companies/{id}", companyId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Cweluch"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nip").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("t@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.linkedinLink").value("https://www.linkedin.com/company/test-company"))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Response JSON: " + responseContent);
    }

    @Test
    void testGetAllCompanies() throws Exception{

        List<CompanyResponseDTO> responseDtoList = List.of(
                new CompanyResponseDTO(
                        "Cweluch",
                        "1234567890",
                        "t@gmail.com",
                        "https://www.linkedin.com/company/test-company"),
                new CompanyResponseDTO(
                        "Marki Company",
                        "1234567890",
                        "marki@gmail.com",
                        "https://www.linkedin.com/company/test-company")
        );

        Mockito.when(companyService.getAllCompanies()).thenReturn(responseDtoList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Cweluch"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nip").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("t@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Marki Company"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nip").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("marki@gmail.com"))
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Response JSON: " + responseContent);
    }


}
