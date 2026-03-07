package com.health.lifestyle.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diseases")
public class Disease {

    @Id
    private String id;

    private String name;

    private Map<String, String> allowedFoods;
    private Map<String, String> avoidFoods;
    private Map<String, String> recommendedExercises;

    private String sleepGuidelines;
    private String stressGuidelines;

    // --- Getters and Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Map<String, String> getAllowedFoods() { return allowedFoods; }
    public void setAllowedFoods(Map<String, String> allowedFoods) { this.allowedFoods = allowedFoods; }

    public Map<String, String> getAvoidFoods() { return avoidFoods; }
    public void setAvoidFoods(Map<String, String> avoidFoods) { this.avoidFoods = avoidFoods; }

    public Map<String, String> getRecommendedExercises() { return recommendedExercises; }
    public void setRecommendedExercises(Map<String, String> recommendedExercises) {
        this.recommendedExercises = recommendedExercises;
    }

    public String getSleepGuidelines() { return sleepGuidelines; }
    public void setSleepGuidelines(String sleepGuidelines) { this.sleepGuidelines = sleepGuidelines; }

    public String getStressGuidelines() { return stressGuidelines; }
    public void setStressGuidelines(String stressGuidelines) { this.stressGuidelines = stressGuidelines; }
}