package com.finance.dashboard.dto.dashboard;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class MonthlyTrendDTO {
    private String month;
    private BigDecimal income;
    private BigDecimal expense;
}