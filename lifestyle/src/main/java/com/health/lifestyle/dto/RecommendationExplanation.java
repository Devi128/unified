package com.health.lifestyle.dto;

public class RecommendationExplanation {

    private String item;
    private String type;
    private String reason;

    public RecommendationExplanation() {
    }

    public RecommendationExplanation(String item, String type, String reason) {
        this.item = item;
        this.type = type;
        this.reason = reason;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}