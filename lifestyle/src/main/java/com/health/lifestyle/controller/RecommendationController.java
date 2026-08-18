package com.health.lifestyle.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.lifestyle.dto.TrackRequest;
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

    // ✅ Get unified multi-disease recommendation
    @GetMapping("/unified/{userId}")
    public UnifiedLifestyleResponse getUnifiedPlan(
            @PathVariable String userId) {

        return recommendationService.getUnifiedPlan(userId);
    }

    // ✅ Track daily lifestyle data
    @PostMapping("/track/{userId}")
    public DailyHealthLog trackDailyProgress(
            @PathVariable String userId,
            @RequestBody TrackRequest request) {

        return recommendationService.trackDailyProgress(
                userId,
                request.getFoodsConsumed(),
                request.getExercisesDone(),
                request.getSleepHours(),
                request.getDate()
        );
    }

    // ✅ Weekly analytics
    @GetMapping("/weekly/{userId}")
    public WeeklyAnalyticsResponse getWeeklyAnalytics(
            @PathVariable String userId) {

        return recommendationService.getWeeklyAnalytics(userId);
    }

    // ✅ Complete Health History
    @GetMapping("/history/{userId}")
    public List<DailyHealthLog> getHealthHistory(
            @PathVariable String userId) {

        return recommendationService.getHealthHistory(userId);

    }
}