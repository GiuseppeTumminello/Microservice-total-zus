package com.acoustic.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.acoustic.rate.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TotalZusService implements SalaryCalculatorService{

    private final RatesConfigurationProperties ratesConfigurationProperties;


    @Override
    public String getDescription() {
        return "Total zus";
    }

    @Override
    public BigDecimal apply(final BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(ratesConfigurationProperties.getTotalZusRate()).setScale(2, RoundingMode.HALF_EVEN);
    }
}
