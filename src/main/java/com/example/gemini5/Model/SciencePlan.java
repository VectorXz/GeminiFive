package com.example.gemini5.Model;

import java.util.Date;

public class SciencePlan {
    private String name;
    private Double funding;
    private String objective;
    private String starsystem;
    private String date; // Not sure about this
    private String time;
    private String location;
    private String requirement;
    private String imageprocessing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFunding() {
        return funding;
    }

    public void setFunding(Double funding) {
        this.funding = funding;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getStarsystem() {
        return starsystem;
    }

    public void setStarsystem(String starsystem) {
        this.starsystem = starsystem;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getImageprocessing() {
        return imageprocessing;
    }

    public void setImageprocessing(String imageprocessing) {
        this.imageprocessing = imageprocessing;
    }
}
