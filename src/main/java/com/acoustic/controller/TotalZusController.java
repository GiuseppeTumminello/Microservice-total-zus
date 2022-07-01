package com.acoustic.controller;


import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acoustic.entity.TotalZus;
import com.acoustic.rate.RatesConfigurationProperties;
import com.acoustic.repository.TotalZusRepository;
import com.acoustic.service.SalaryCalculatorService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/totalZus")
@RequiredArgsConstructor
@Validated
public class TotalZusController {

    private final TotalZusRepository totalZusRepository;
    private final SalaryCalculatorService salaryCalculatorService;
    private final RatesConfigurationProperties ratesConfigurationProperties;


    @PostMapping("/getTotalZus/{grossMonthlySalary}")
    public Map<String, BigDecimal> calculateTotalZus(@PathVariable @Min(2000)BigDecimal grossMonthlySalary){
        var totalZus = salaryCalculatorService.apply(grossMonthlySalary);
        this.totalZusRepository.save(TotalZus.builder().totalZusAmount(totalZus).totalZusRate(ratesConfigurationProperties.getTotalZusRate()).build());
        return new LinkedHashMap<>(Map.of(salaryCalculatorService.getDescription(), totalZus));
    }
}
