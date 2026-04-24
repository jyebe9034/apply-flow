package com.hannah.applyflow.dashboard.controller;

import com.hannah.applyflow.dashboard.dto.DashboardResponse;
import com.hannah.applyflow.dashboard.service.DashboardService;
import com.hannah.applyflow.global.response.ApiResponse;
import com.hannah.applyflow.global.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Dashboard API", description = "Endpoints for retrieving job application statistics and overview data")
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(
            summary = "Get recruitment dashboard summary",
            description = "Provides a comprehensive overview of the job search progress. " +
                    "It includes **application counts categorized by status** (e.g., Applied, Interview), " +
                    "a list of **recently submitted applications**, and a prioritized schedule of **upcoming interviews**."
    )
    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        DashboardResponse data = dashboardService.getDashboard(userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.success("Dashboard retrieved successfully.", data));
    }
}
