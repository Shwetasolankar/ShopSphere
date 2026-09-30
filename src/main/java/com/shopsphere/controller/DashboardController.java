package com.shopsphere.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopsphere.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // ==============================
    // DASHBOARD SUMMARY
    // ==============================

    @GetMapping
    public Map<String, Object> getDashboardSummary() {

        return dashboardService.getDashboardSummary();
    }
}