package com.health.lifestyle.dto;

import java.util.List;

public class TrackRequest {

    private List<String> foodsConsumed;
    private List<String> exercisesDone;
    private int sleepHours;
    private String date;
    
    // Getters & Setters

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
    public String getDate() {
    return date;
}

public void setDate(String date) {
    this.date = date;
}
}