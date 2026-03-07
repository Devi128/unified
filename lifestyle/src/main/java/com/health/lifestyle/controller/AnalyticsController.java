package com.health.lifestyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.lifestyle.dto.WeeklyAnalyticsResponse;
import com.health.lifestyle.service.RecommendationService;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/weekly/{userId}")
    public WeeklyAnalyticsResponse getWeeklyAnalytics(@PathVariable String userId) {
        return recommendationService.getWeeklyAnalytics(userId);
    }
}