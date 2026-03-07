package com.health.lifestyle.dto;

import java.util.Map;

public class UnifiedLifestyleResponse {

    private Map<String, String> finalAllowedFoods;
    private Map<String, String> finalAvoidFoods;
    private Map<String, String> finalExercises;

    private String sleepAdvice;
    private String stressAdvice;

    // --- Getters & Setters ---

    public Map<String, String> getFinalAllowedFoods() {
        return finalAllowedFoods;
    }

    public void setFinalAllowedFoods(Map<String, String> finalAllowedFoods) {
        this.finalAllowedFoods = finalAllowedFoods;
    }

    public Map<String, String> getFinalAvoidFoods() {
        return finalAvoidFoods;
    }

    public void setFinalAvoidFoods(Map<String, String> finalAvoidFoods) {
        this.finalAvoidFoods = finalAvoidFoods;
    }

    public Map<String, String> getFinalExercises() {
        return finalExercises;
    }

    public void setFinalExercises(Map<String, String> finalExercises) {
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
}