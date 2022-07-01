package com.acoustic.integrationtestcontroller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TotalZusControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final String TOTAL_ZUS_ENDPOINT = "/totalZus/getTotalZus";


    @ParameterizedTest
    @CsvSource({"6000,822.60", "7000, 959.70", "8555,1172.89", "15143.99,2076.24"})
    public void calculateTotalZus(BigDecimal input, BigDecimal totalZus) throws Exception {
        var expected = this.objectMapper.writeValueAsString(Map.of("Total zus", totalZus));
        this.mockMvc.perform(post("/totalZus/getTotalZus/" + input).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

}