package com.health.lifestyle.dto;
import java.util.List;
public class WeeklyAnalyticsResponse {

    private double averageHealthScore;
    private double averageDietScore;
    private double averageExerciseScore;
    private double averageSleepScore;

    private int totalDaysTracked;
    private List<DailyScoreResponse> weeklyScores;
    
    // Getters & Setters

    public double getAverageHealthScore() {
        return averageHealthScore;
    }

    public void setAverageHealthScore(double averageHealthScore) {
        this.averageHealthScore = averageHealthScore;
    }

    public double getAverageDietScore() {
        return averageDietScore;
    }

    public void setAverageDietScore(double averageDietScore) {
        this.averageDietScore = averageDietScore;
    }

    public double getAverageExerciseScore() {
        return averageExerciseScore;
    }

    public void setAverageExerciseScore(double averageExerciseScore) {
        this.averageExerciseScore = averageExerciseScore;
    }

    public double getAverageSleepScore() {
        return averageSleepScore;
    }

    public void setAverageSleepScore(double averageSleepScore) {
        this.averageSleepScore = averageSleepScore;
    }

    public int getTotalDaysTracked() {
        return totalDaysTracked;
    }

    public void setTotalDaysTracked(int totalDaysTracked) {
        this.totalDaysTracked = totalDaysTracked;
    }

    public List<DailyScoreResponse> getWeeklyScores() {
        return weeklyScores;
    }

    public void setWeeklyScores(List<DailyScoreResponse> weeklyScores) {
        this.weeklyScores = weeklyScores;
    }
}