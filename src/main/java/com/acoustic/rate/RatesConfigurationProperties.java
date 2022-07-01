package com.acoustic.rate;


import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "rate")
@PropertySource(value = "classpath:rates.properties")
@Configuration
public class RatesConfigurationProperties {

    private BigDecimal totalZusRate;

}
