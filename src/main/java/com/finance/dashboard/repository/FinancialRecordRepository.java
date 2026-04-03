package com.finance.dashboard.repository;

import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.enums.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    List<FinancialRecord> findByUserId(Long userId);
    List<FinancialRecord> findByUserIdAndType(Long userId, RecordType type);
    List<FinancialRecord> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}