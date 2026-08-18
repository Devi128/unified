package com.health.lifestyle.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diseases")
public class Disease {

    @Id
    private String id;

    private String name;

    private List<String> allowedFoods;
    private List<String> avoidFoods;
    private List<String> recommendedExercises;

    private String sleepGuidelines;
    private String stressGuidelines;

    // --- Getters and Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAllowedFoods() {
        return allowedFoods;
    }

    public void setAllowedFoods(List<String> allowedFoods) {
        this.allowedFoods = allowedFoods;
    }

    public List<String> getAvoidFoods() {
        return avoidFoods;
    }

    public void setAvoidFoods(List<String> avoidFoods) {
        this.avoidFoods = avoidFoods;
    }

    public List<String> getRecommendedExercises() {
        return recommendedExercises;
    }

    public void setRecommendedExercises(List<String> recommendedExercises) {
        this.recommendedExercises = recommendedExercises;
    }

    public String getSleepGuidelines() {
        return sleepGuidelines;
    }

    public void setSleepGuidelines(String sleepGuidelines) {
        this.sleepGuidelines = sleepGuidelines;
    }

    public String getStressGuidelines() {
        return stressGuidelines;
    }

    public void setStressGuidelines(String stressGuidelines) {
        this.stressGuidelines = stressGuidelines;
    }
}