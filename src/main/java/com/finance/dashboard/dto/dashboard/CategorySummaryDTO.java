package com.finance.dashboard.dto.dashboard;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class CategorySummaryDTO {
    private String category;
    private BigDecimal totalAmount; 
}