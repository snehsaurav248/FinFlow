package com.finance.dashboard.service;

import com.finance.dashboard.dto.dashboard.*;

import java.util.List;

public interface DashboardService {

    DashboardSummaryDTO getSummary();

    List<CategorySummaryDTO> getCategorySummary();

    List<MonthlyTrendDTO> getMonthlyTrends();
}