package com.health.lifestyle.service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import com.health.lifestyle.dto.DailyScoreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.health.lifestyle.dto.UnifiedLifestyleResponse;
import com.health.lifestyle.dto.RecommendationExplanation;
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

    // 🔥 Unified Multi-Disease Recommendation
    public UnifiedLifestyleResponse getUnifiedPlan(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID: " + userId));

        List<String> userDiseaseNames = user.getDiseases();

        if (userDiseaseNames == null || userDiseaseNames.isEmpty()) {
            throw new RuntimeException(
                    "User has no diseases assigned.");
        }

        // ✅ Using Set to avoid duplicates
        Set<String> allowedFoods = new HashSet<>();
        Set<String> avoidFoods = new HashSet<>();
        Set<String> exercises = new HashSet<>();
        List<RecommendationExplanation> explanationList = new ArrayList<>();
        List<String> sleepList = new ArrayList<>();
        List<String> stressList = new ArrayList<>();
        // 🔥 Combine recommendations from multiple diseases
        for (String diseaseName : userDiseaseNames) {

            Disease disease =
                    diseaseRepository.findByNameIgnoreCase(
                            diseaseName);

            // Skip if disease not found
            if (disease == null) {
                continue;
            }

            // ✅ Allowed foods
// ✅ Allowed foods
        if (disease.getAllowedFoods() != null) {

        allowedFoods.addAll(disease.getAllowedFoods());

        for (String food : disease.getAllowedFoods()) {

                RecommendationExplanation exp =
                        new RecommendationExplanation();

                exp.setItem(food);
                exp.setType("Recommended Food");
                exp.setReason(getFoodReason(food));

                explanationList.add(exp);
        }
        }

        // ✅ Avoid foods
        if (disease.getAvoidFoods() != null) {

        avoidFoods.addAll(disease.getAvoidFoods());

        for (String food : disease.getAvoidFoods()) {

                RecommendationExplanation exp =
                        new RecommendationExplanation();

                exp.setItem(food);
                exp.setType("Avoid Food");
                exp.setReason(getAvoidReason(food));

                explanationList.add(exp);
        }
        }

            // ✅ Exercises
// ✅ Exercises
        if (disease.getRecommendedExercises() != null) {

        exercises.addAll(disease.getRecommendedExercises());

        for (String ex : disease.getRecommendedExercises()) {

                RecommendationExplanation exp =
                        new RecommendationExplanation();

                exp.setItem(ex);
                exp.setType("Exercise");
                exp.setReason(getExerciseReason(ex));

                explanationList.add(exp);
        }
        }

            // ✅ Sleep advice
            if (disease.getSleepGuidelines() != null) {
                sleepList.add(
                        disease.getSleepGuidelines());
            }

            // ✅ Stress advice
            if (disease.getStressGuidelines() != null) {
                stressList.add(
                        disease.getStressGuidelines());
            }
        }

        // 🔥 Conflict Resolution
        allowedFoods.removeAll(avoidFoods);

        // ✅ Build final response
        UnifiedLifestyleResponse response =
                new UnifiedLifestyleResponse();

        response.setFinalAllowedFoods(
                allowedFoods);

        response.setFinalAvoidFoods(
                avoidFoods);

        response.setFinalExercises(
                exercises);

        response.setSleepAdvice(
                String.join(" | ", sleepList));

        response.setStressAdvice(
                String.join(" | ", stressList));

        response.setExplanations(
                explanationList);

        return response;
    }

    // 🔥 Daily Tracking + Health Scoring
    // 🔥 Daily Tracking + Health Scoring
public DailyHealthLog trackDailyProgress(
        String userId,
        List<String> foodsConsumed,
        List<String> exercisesDone,
        int sleepHours,
        String selectedDate) {

    UnifiedLifestyleResponse plan =
            getUnifiedPlan(userId);

    int dietScore = 0;
    int exerciseScore = 0;
    int sleepScore = 0;

    // ✅ Diet Score (0 - 35)
    if (foodsConsumed != null && !foodsConsumed.isEmpty()) {

        int healthyFoods = 0;

        for (String food : foodsConsumed) {

            if (plan.getFinalAllowedFoods().contains(food)) {
                healthyFoods++;
            }

            if (plan.getFinalAvoidFoods().contains(food)) {
                dietScore -= 10;
            }
        }

        dietScore += healthyFoods * 15;

        if (dietScore > 35) {
            dietScore = 35;
        }

        if (dietScore < 0) {
            dietScore = 0;
        }
    }

    // ✅ Exercise Score (0 - 35)
    if (exercisesDone != null && !exercisesDone.isEmpty()) {

        int matchedExercises = 0;

        for (String ex : exercisesDone) {

            if (plan.getFinalExercises().contains(ex)) {
                matchedExercises++;
            }
        }

        exerciseScore = matchedExercises * 18;

        if (exerciseScore > 35) {
            exerciseScore = 35;
        }
    }

    // ✅ Sleep Score (0 - 30)
    if (sleepHours >= 7 && sleepHours <= 8) {

        sleepScore = 30;

    } else if (sleepHours >= 6) {

        sleepScore = 20;

    } else if (sleepHours >= 5) {

        sleepScore = 10;

    } else {

        sleepScore = 0;
    }

    // ✅ Final Score (0 - 100)
    int overallScore =
            dietScore +
            exerciseScore +
            sleepScore;

    if (overallScore > 100) {
        overallScore = 100;
    }
// ✅ Determine selected date
LocalDate dateToSave;

if (selectedDate != null && !selectedDate.isBlank()) {
    dateToSave = LocalDate.parse(selectedDate);
} else {
    dateToSave = LocalDate.now();
}

// ✅ Check if a log already exists for this user and date
DailyHealthLog log =
        dailyHealthLogRepository.findByUserIdAndDate(
                userId,
                dateToSave
        );

// ✅ If not found, create a new one
if (log == null) {
    log = new DailyHealthLog();
    log.setUserId(userId);
    log.setDate(dateToSave);
}

// ✅ Update the log
log.setFoodsConsumed(foodsConsumed);

log.setExercisesDone(exercisesDone);

log.setSleepHours(sleepHours);

log.setDietScore(dietScore);

log.setExerciseScore(exerciseScore);

log.setSleepScore(sleepScore);

log.setOverallHealthScore(overallScore);

return dailyHealthLogRepository.save(log);
}

    // 🔥 Weekly Analytics
    public WeeklyAnalyticsResponse
    getWeeklyAnalytics(String userId) {

        List<DailyHealthLog> logs =
                dailyHealthLogRepository.findByUserId(userId);

        logs.sort((a, b) ->
                a.getDate().compareTo(b.getDate()));

        if (logs.size() > 7) {

        logs = logs.subList(
                logs.size() - 7,
                logs.size()
        );
        }

        if (logs.isEmpty()) {

    WeeklyAnalyticsResponse emptyResponse =
            new WeeklyAnalyticsResponse();

    emptyResponse.setAverageHealthScore(0);

    emptyResponse.setAverageDietScore(0);

    emptyResponse.setAverageExerciseScore(0);

    emptyResponse.setAverageSleepScore(0);

    emptyResponse.setTotalDaysTracked(0);

    return emptyResponse;
}
        double totalHealth = 0;
        double totalDiet = 0;
        double totalExercise = 0;
        double totalSleep = 0;

        for (DailyHealthLog log : logs) {

            totalHealth +=
                    log.getOverallHealthScore();

            totalDiet +=
                    log.getDietScore();

            totalExercise +=
                    log.getExerciseScore();

            totalSleep +=
                    log.getSleepScore();
        }

        int days = logs.size();
Map<LocalDate, List<Integer>> groupedScores = new LinkedHashMap<>();

for (DailyHealthLog log : logs) {

    groupedScores
            .computeIfAbsent(
                    log.getDate(),
                    k -> new ArrayList<>())
            .add(log.getOverallHealthScore());
}

        List<DailyScoreResponse> weeklyScores = new ArrayList<>();

        for (Map.Entry<LocalDate, List<Integer>> entry : groupedScores.entrySet()) {

        int total = 0;

        for (int score : entry.getValue()) {
                total += score;
        }

        int average = total / entry.getValue().size();

        String day =
                entry.getKey().getDayOfMonth()
                + " "
                + entry.getKey()
                        .getMonth()
                        .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

        weeklyScores.add(
                new DailyScoreResponse(day, average)
        );
        }
        WeeklyAnalyticsResponse response =
                new WeeklyAnalyticsResponse();

        response.setAverageHealthScore(
                totalHealth / days);

        response.setAverageDietScore(
                totalDiet / days);

        response.setAverageExerciseScore(
                totalExercise / days);

        response.setAverageSleepScore(
                totalSleep / days);

        response.setTotalDaysTracked(
                days);
        response.setWeeklyScores(weeklyScores);
        return response;
    }

    // 🔥 Get Complete Health History
        public List<DailyHealthLog> getHealthHistory(String userId) {

                return dailyHealthLogRepository.findByUserId(userId);

        }
        private String getFoodReason(String food) {

    switch (food.toLowerCase()) {

        case "vegetables":
            return "Rich in vitamins and antioxidants that improve overall health.";

        case "whole grains":
            return "Provide slow-release energy and help regulate blood sugar.";

        case "lean protein":
            return "Supports muscle health and improves metabolism.";

        case "high fiber foods":
            return "Improves digestion and helps control blood sugar.";

        default:
            return "Recommended as part of a healthy lifestyle.";
    }
}

private String getAvoidReason(String food) {

    switch (food.toLowerCase()) {

        case "sugary foods":
            return "May increase blood sugar and worsen symptoms.";

        case "processed carbs":
            return "Can contribute to insulin resistance.";

        case "soft drinks":
            return "Contain excess sugar with very little nutritional value.";

        default:
            return "Avoid to better manage your health condition.";
    }
}

private String getExerciseReason(String exercise) {

    switch (exercise.toLowerCase()) {

        case "walking":
            return "Improves heart health and insulin sensitivity.";

        case "yoga":
            return "Reduces stress and improves flexibility.";

        case "strength training":
            return "Builds muscle and supports healthy metabolism.";

        default:
            return "Recommended for maintaining overall fitness.";
    }
}
}