package com.acoustic.entity;


import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalZus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private BigDecimal totalZusAmount;
    private BigDecimal totalZusRate;


}
