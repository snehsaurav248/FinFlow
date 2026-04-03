package com.finance.dashboard.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MonthlyTrendDTO {

    private String month;
    private BigDecimal income;
    private BigDecimal expense;
}