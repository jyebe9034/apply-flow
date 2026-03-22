package com.hannah.applyflow.dashboard.controller;

import com.hannah.applyflow.dashboard.dto.DashboardResponse;
import com.hannah.applyflow.dashboard.service.DashboardService;
import com.hannah.applyflow.global.response.ApiResponse;
import com.hannah.applyflow.global.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponse>> getDashboard(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        DashboardResponse data = dashboardService.getDashboard(userDetails.getUser());
        return ResponseEntity.ok(ApiResponse.success("Dashboard retrieved successfully.", data));
    }
}
