package com.health.lifestyle.dto;

import java.util.List;
import java.util.Set;

public class UnifiedLifestyleResponse {

    private Set<String> finalAllowedFoods;
    private Set<String> finalAvoidFoods;
    private Set<String> finalExercises;

    private String sleepAdvice;
    private String stressAdvice;

    // ⭐ NEW: Explanation List
    private List<RecommendationExplanation> explanations;

    // ---------------- Getters & Setters ----------------

    public Set<String> getFinalAllowedFoods() {
        return finalAllowedFoods;
    }

    public void setFinalAllowedFoods(Set<String> finalAllowedFoods) {
        this.finalAllowedFoods = finalAllowedFoods;
    }

    public Set<String> getFinalAvoidFoods() {
        return finalAvoidFoods;
    }

    public void setFinalAvoidFoods(Set<String> finalAvoidFoods) {
        this.finalAvoidFoods = finalAvoidFoods;
    }

    public Set<String> getFinalExercises() {
        return finalExercises;
    }

    public void setFinalExercises(Set<String> finalExercises) {
        this.finalExercises = finalExercises;
    }

    public String getSleepAdvice() {
        return sleepAdvice;
    }

    public void setSleepAdvice(String sleepAdvice) {
        this.sleepAdvice = sleepAdvice;
    }

    public String getStressAdvice() {
        return stressAdvice;
    }

    public void setStressAdvice(String stressAdvice) {
        this.stressAdvice = stressAdvice;
    }

    // ⭐ NEW
    public List<RecommendationExplanation> getExplanations() {
        return explanations;
    }

    public void setExplanations(List<RecommendationExplanation> explanations) {
        this.explanations = explanations;
    }
}