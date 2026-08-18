package com.health.lifestyle.dto;

public class DailyScoreResponse {

    private String day;
    private int lifestyleScore;

    public DailyScoreResponse() {
    }

    public DailyScoreResponse(String day, int lifestyleScore) {
        this.day = day;
        this.lifestyleScore = lifestyleScore;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getLifestyleScore() {
        return lifestyleScore;
    }

    public void setLifestyleScore(int lifestyleScore) {
        this.lifestyleScore = lifestyleScore;
    }
}