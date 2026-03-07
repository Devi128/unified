package com.health.lifestyle.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "daily_health_logs")
public class DailyHealthLog {

    @Id
    private String id;

    private String userId;
    private LocalDate date;

    private List<String> foodsConsumed;
    private List<String> exercisesDone;
    private int sleepHours;

    private int dietScore;
    private int exerciseScore;
    private int sleepScore;
    private int overallHealthScore;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getFoodsConsumed() {
        return foodsConsumed;
    }

    public void setFoodsConsumed(List<String> foodsConsumed) {
        this.foodsConsumed = foodsConsumed;
    }

    public List<String> getExercisesDone() {
        return exercisesDone;
    }

    public void setExercisesDone(List<String> exercisesDone) {
        this.exercisesDone = exercisesDone;
    }

    public int getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(int sleepHours) {
        this.sleepHours = sleepHours;
    }

    public int getDietScore() {
        return dietScore;
    }

    public void setDietScore(int dietScore) {
        this.dietScore = dietScore;
    }

    public int getExerciseScore() {
        return exerciseScore;
    }

    public void setExerciseScore(int exerciseScore) {
        this.exerciseScore = exerciseScore;
    }

    public int getSleepScore() {
        return sleepScore;
    }

    public void setSleepScore(int sleepScore) {
        this.sleepScore = sleepScore;
    }

    public int getOverallHealthScore() {
        return overallHealthScore;
    }

    public void setOverallHealthScore(int overallHealthScore) {
        this.overallHealthScore = overallHealthScore;
    }
}