package com.acoustic.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.acoustic.rate.RatesConfigurationProperties;


@ExtendWith(MockitoExtension.class)
class TotalZusServiceTest {

    @InjectMocks
    private  TotalZusService salaryCalculatorService;

    @Mock
    private RatesConfigurationProperties ratesConfigurationProperties;

    @Test
    void getDescription() {
        assertThat(salaryCalculatorService.getDescription()).isEqualTo("Total zus");
    }

    @ParameterizedTest
    @CsvSource({"6000, 822.60, 0.1371", "7000, 959.70, 0.1371", "15891.68, 2178.75, 0.1371"})
    public void getTotalZus(BigDecimal input, BigDecimal expected, BigDecimal rate) {
        given(ratesConfigurationProperties.getTotalZusRate()).willReturn(rate);
        assertThat(salaryCalculatorService.apply(input)).isEqualTo(expected);
    }
}