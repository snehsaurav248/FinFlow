package com.finance.dashboard.service.impl;

import com.finance.dashboard.dto.dashboard.*;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.enums.RecordType;
import com.finance.dashboard.exception.ResourceNotFoundException;
import com.finance.dashboard.repository.FinancialRecordRepository;
import com.finance.dashboard.repository.UserRepository;
import com.finance.dashboard.service.DashboardService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final FinancialRecordRepository recordRepository;
    private final UserRepository userRepository;

    // ✅ Get logged-in user
    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // 🔹 1. Summary
    @Override
    public DashboardSummaryDTO getSummary() {

        List<FinancialRecord> records =
                recordRepository.findByUserId(getCurrentUser().getId());

        BigDecimal totalIncome = records.stream()
                .filter(r -> r.getType() == RecordType.INCOME)
                .map(FinancialRecord::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpense = records.stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .map(FinancialRecord::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return DashboardSummaryDTO.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .balance(totalIncome.subtract(totalExpense))
                .build();
    }

    // 🔹 2. Category Summary
    @Override
    public List<CategorySummaryDTO> getCategorySummary() {

        List<FinancialRecord> records =
                recordRepository.findByUserId(getCurrentUser().getId());

        Map<String, BigDecimal> categoryMap = records.stream()
                .filter(r -> r.getType() == RecordType.EXPENSE)
                .collect(Collectors.groupingBy(
                        FinancialRecord::getCategory,
                        Collectors.mapping(
                                FinancialRecord::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));

        return categoryMap.entrySet().stream()
                .map(entry -> new CategorySummaryDTO(entry.getKey(), entry.getValue()))
                .toList();
    }

    // 🔹 3. Monthly Trends
    @Override
    public List<MonthlyTrendDTO> getMonthlyTrends() {

        List<FinancialRecord> records =
                recordRepository.findByUserId(getCurrentUser().getId());

        Map<String, List<FinancialRecord>> grouped =
                records.stream().collect(Collectors.groupingBy(
                        r -> r.getDate().getMonth()
                                .getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                ));

        List<MonthlyTrendDTO> result = new ArrayList<>();

        for (Map.Entry<String, List<FinancialRecord>> entry : grouped.entrySet()) {

            BigDecimal income = entry.getValue().stream()
                    .filter(r -> r.getType() == RecordType.INCOME)
                    .map(FinancialRecord::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal expense = entry.getValue().stream()
                    .filter(r -> r.getType() == RecordType.EXPENSE)
                    .map(FinancialRecord::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            result.add(new MonthlyTrendDTO(entry.getKey(), income, expense));
        }

        return result;
    }
}