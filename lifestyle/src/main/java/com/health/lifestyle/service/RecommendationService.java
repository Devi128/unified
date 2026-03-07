package com.health.lifestyle.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.lifestyle.dto.UnifiedLifestyleResponse;
import com.health.lifestyle.dto.WeeklyAnalyticsResponse;
import com.health.lifestyle.model.DailyHealthLog;
import com.health.lifestyle.model.Disease;
import com.health.lifestyle.model.User;
import com.health.lifestyle.repository.DailyHealthLogRepository;
import com.health.lifestyle.repository.DiseaseRepository;
import com.health.lifestyle.repository.UserRepository;
@Service
public class RecommendationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private DailyHealthLogRepository dailyHealthLogRepository;

    // 🔥 STEP 9 – Unified Plan with Explainable Data
    public UnifiedLifestyleResponse getUnifiedPlan(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        List<String> userDiseaseNames = user.getDiseases();

        if (userDiseaseNames == null || userDiseaseNames.isEmpty()) {
            throw new RuntimeException("User has no diseases assigned.");
        }

        Map<String, String> allowedFoods = new HashMap<>();
        Map<String, String> avoidFoods = new HashMap<>();
        Map<String, String> exercises = new HashMap<>();

        List<String> sleepList = new ArrayList<>();
        List<String> stressList = new ArrayList<>();

        for (String diseaseName : userDiseaseNames) {

            Disease disease = diseaseRepository.findByName(diseaseName);

            if (disease == null) {
                throw new RuntimeException("Disease not found: " + diseaseName);
            }

            if (disease.getAllowedFoods() != null)
                allowedFoods.putAll(disease.getAllowedFoods());

            if (disease.getAvoidFoods() != null)
                avoidFoods.putAll(disease.getAvoidFoods());

            if (disease.getRecommendedExercises() != null)
                exercises.putAll(disease.getRecommendedExercises());

            if (disease.getSleepGuidelines() != null)
                sleepList.add(disease.getSleepGuidelines());

            if (disease.getStressGuidelines() != null)
                stressList.add(disease.getStressGuidelines());
        }

        // 🔥 Conflict Resolution
        for (String avoid : avoidFoods.keySet()) {
            allowedFoods.remove(avoid);
        }

        UnifiedLifestyleResponse response = new UnifiedLifestyleResponse();
        response.setFinalAllowedFoods(allowedFoods);
        response.setFinalAvoidFoods(avoidFoods);
        response.setFinalExercises(exercises);
        response.setSleepAdvice(String.join(" | ", sleepList));
        response.setStressAdvice(String.join(" | ", stressList));

        return response;
    }

    // 🔥 STEP 10 – Tracking + Scoring (FIXED)
    public DailyHealthLog trackDailyProgress(
            String userId,
            List<String> foodsConsumed,
            List<String> exercisesDone,
            int sleepHours) {

        UnifiedLifestyleResponse plan = getUnifiedPlan(userId);

        int dietScore = 0;
        int exerciseScore = 0;
        int sleepScore = 0;

        // ✅ FIXED: using containsKey()
        if (foodsConsumed != null) {
            for (String food : foodsConsumed) {

                if (plan.getFinalAllowedFoods().containsKey(food))
                    dietScore += 10;

                if (plan.getFinalAvoidFoods().containsKey(food))
                    dietScore -= 15;
            }
        }

        if (exercisesDone != null) {
            for (String ex : exercisesDone) {
                if (plan.getFinalExercises().containsKey(ex))
                    exerciseScore += 10;
            }
        }

        if (sleepHours >= 7 && sleepHours <= 8)
            sleepScore = 20;
        else if (sleepHours >= 6)
            sleepScore = 10;
        else
            sleepScore = 5;

        int overallScore = Math.max(0,
                Math.min(100, dietScore + exerciseScore + sleepScore));

        DailyHealthLog log = new DailyHealthLog();
        log.setUserId(userId);
        log.setDate(LocalDate.now());
        log.setFoodsConsumed(foodsConsumed);
        log.setExercisesDone(exercisesDone);
        log.setSleepHours(sleepHours);
        log.setDietScore(dietScore);
        log.setExerciseScore(exerciseScore);
        log.setSleepScore(sleepScore);
        log.setOverallHealthScore(overallScore);

        return dailyHealthLogRepository.save(log);
    }


public WeeklyAnalyticsResponse getWeeklyAnalytics(String userId) {

    LocalDate today = LocalDate.now();
    LocalDate weekStart = today.minusDays(6);

    List<DailyHealthLog> logs =
            dailyHealthLogRepository.findByUserIdAndDateBetween(
                    userId, weekStart, today);

    if (logs.isEmpty()) {
        throw new RuntimeException("No tracking data available for this week.");
    }

    double totalHealth = 0;
    double totalDiet = 0;
    double totalExercise = 0;
    double totalSleep = 0;

    for (DailyHealthLog log : logs) {
        totalHealth += log.getOverallHealthScore();
        totalDiet += log.getDietScore();
        totalExercise += log.getExerciseScore();
        totalSleep += log.getSleepScore();
    }

    WeeklyAnalyticsResponse response = new WeeklyAnalyticsResponse();

    int days = logs.size();

    response.setAverageHealthScore(totalHealth / days);
    response.setAverageDietScore(totalDiet / days);
    response.setAverageExerciseScore(totalExercise / days);
    response.setAverageSleepScore(totalSleep / days);
    response.setTotalDaysTracked(days);

    return response;
}
}