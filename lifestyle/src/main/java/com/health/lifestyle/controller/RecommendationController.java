package com.health.lifestyle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.health.lifestyle.dto.UnifiedLifestyleResponse;
import com.health.lifestyle.dto.WeeklyAnalyticsResponse;
import com.health.lifestyle.model.DailyHealthLog;
import com.health.lifestyle.service.RecommendationService;

@RestController
@RequestMapping("/recommendation")
@CrossOrigin(origins = "*")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    // Get lifestyle recommendation
    @GetMapping("/unified/{userId}")
    public UnifiedLifestyleResponse getUnifiedPlan(@PathVariable String userId) {
        return recommendationService.getUnifiedPlan(userId);
    }

    // Track daily health
    @PostMapping("/track/{userId}")
    public DailyHealthLog trackDailyProgress(
            @PathVariable String userId,
            @RequestParam List<String> foodsConsumed,
            @RequestParam List<String> exercisesDone,
            @RequestParam int sleepHours) {

        return recommendationService.trackDailyProgress(
                userId, foodsConsumed, exercisesDone, sleepHours);
    }

    // Weekly analytics
    @GetMapping("/weekly/{userId}")
    public WeeklyAnalyticsResponse getWeeklyAnalytics(@PathVariable String userId) {
        return recommendationService.getWeeklyAnalytics(userId);
    }
}