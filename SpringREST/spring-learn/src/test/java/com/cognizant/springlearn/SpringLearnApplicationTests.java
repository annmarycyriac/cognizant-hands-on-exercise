package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.cognizant.springlearn.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.Base64;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringLearnApplicationTests {

    @Autowired
    private CountryController countryController;

    @Autowired
    private MockMvc mvc;

    @Test
    public void contextLoads() {
        assertNotNull(countryController);
    }

    private String obtainToken() throws Exception {
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString("user:pwd".getBytes());
        String response = mvc.perform(get("/authenticate").header("Authorization", basicAuth))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        
        int start = response.indexOf("\"token\":\"") + 9;
        int end = response.indexOf("\"", start);
        return response.substring(start, end);
    }

    @Test
    public void testGetCountry() throws Exception {
        String token = obtainToken();
        ResultActions actions = mvc.perform(get("/country").header("Authorization", "Bearer " + token));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.code").exists());
        actions.andExpect(jsonPath("$.code").value("IN"));
        actions.andExpect(jsonPath("$.name").exists());
        actions.andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    public void testGetCountryException() throws Exception {
        String token = obtainToken();
        ResultActions actions = mvc.perform(get("/countries/XX").header("Authorization", "Bearer " + token));
        actions.andExpect(status().isNotFound());
    }
}
