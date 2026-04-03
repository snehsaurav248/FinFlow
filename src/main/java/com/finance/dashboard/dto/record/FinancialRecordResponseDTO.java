package com.finance.dashboard.dto.record;

import com.finance.dashboard.enums.RecordType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FinancialRecordResponseDTO {

    private Long id;
    private BigDecimal amount;
    private RecordType type;
    private String category;
    private LocalDate date;
    private String description;
}