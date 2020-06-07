package com.example.gemini5.Model;

import edu.gemini.app.ocs.model.BaseSciencePlan;
import edu.gemini.app.ocs.model.DataProcRequirement;
import jparsec.ephem.Target;

import java.sql.Time;
import java.util.Date;

public class SciPlanForm {
    private String name;
    private double funding;
    private String objective;
    private Target.TARGET starsystem;
    private String date;
    private Time time;
    private BaseSciencePlan.TELESCOPELOC location;
    private DataProcRequirement.TYPE filetype;
    private double quality;
    private DataProcRequirement.COLOR_TYPE color;
    private double contrast;
    private double brightness;
    private double saturation;
    private String creator;

    public Target.TARGET getStarsystem() {
        return starsystem;
    }

    public void setStarsystem(Target.TARGET starsystem) {
        this.starsystem = starsystem;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFunding() {
        return funding;
    }

    public void setFunding(double funding) {
        this.funding = funding;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public BaseSciencePlan.TELESCOPELOC getLocation() {
        return location;
    }

    public void setLocation(BaseSciencePlan.TELESCOPELOC location) {
        this.location = location;
    }

    public DataProcRequirement.TYPE getFiletype() {
        return filetype;
    }

    public void setFiletype(DataProcRequirement.TYPE filetype) {
        this.filetype = filetype;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public DataProcRequirement.COLOR_TYPE getColor() {
        return color;
    }

    public void setColor(DataProcRequirement.COLOR_TYPE color) {
        this.color = color;
    }

    public double getContrast() {
        return contrast;
    }

    public void setContrast(double contrast) {
        this.contrast = contrast;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public double getSaturation() {
        return saturation;
    }

    public void setSaturation(double saturation) {
        this.saturation = saturation;
    }
}
