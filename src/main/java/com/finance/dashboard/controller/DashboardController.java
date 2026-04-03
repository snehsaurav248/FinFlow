package com.finance.dashboard.controller;

import com.finance.dashboard.dto.dashboard.*;
import com.finance.dashboard.service.DashboardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public DashboardSummaryDTO getSummary() {
        return dashboardService.getSummary();
    }

    @GetMapping("/category")
    public List<CategorySummaryDTO> getCategorySummary() {
        return dashboardService.getCategorySummary();
    }

    @GetMapping("/monthly")
    public List<MonthlyTrendDTO> getMonthlyTrends() {
        return dashboardService.getMonthlyTrends();
    }
}