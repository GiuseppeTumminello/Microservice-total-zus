package com.acoustic.controller;


import com.acoustic.entity.TotalZus;
import com.acoustic.rate.RatesConfigurationProperties;
import com.acoustic.repository.TotalZusRepository;
import com.acoustic.service.SalaryCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping("/totalZus")
@RequiredArgsConstructor
@Validated
public class TotalZusController {

    public static final String DESCRIPTION = "description";
    public static final String VALUE = "value";
    private final TotalZusRepository totalZusRepository;
    private final SalaryCalculatorService salaryCalculatorService;
    private final RatesConfigurationProperties ratesConfigurationProperties;


    @PostMapping("/getTotalZus/{grossMonthlySalary}")
    public Map<String, String> calculateTotalZus(@PathVariable @Min(2000)BigDecimal grossMonthlySalary){
        var totalZus = salaryCalculatorService.apply(grossMonthlySalary);
        this.totalZusRepository.save(TotalZus.builder().totalZusAmount(totalZus).totalZusRate(ratesConfigurationProperties.getTotalZusRate()).build());
        return Map.of(DESCRIPTION,salaryCalculatorService.getDescription(), VALUE, String.valueOf(totalZus));
    }
}
