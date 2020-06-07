/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package edu.gemini.app.ocs.model;

import java.util.Objects;

public class DataProcRequirement {
    public enum TYPE {
        RAW, PNG, JPEG, TIFF
    }

    public enum COLOR_TYPE {
        BW, COLOR
    }

    private DataProcRequirement.TYPE fileType;
    private double fileQuality;
    private COLOR_TYPE colorType;
    private double contrast;
    private double brightness;
    private double saturation;

    public DataProcRequirement() {

    }

    public DataProcRequirement(TYPE fileType, double fileQuality,
                               COLOR_TYPE colorType, double colors, double contrast,
                               double brightness, double saturation) {
        this.fileType = fileType;
        this.fileQuality = fileQuality;
        this.colorType = colorType;
        this.contrast = contrast;
        this.brightness = brightness;
        this.saturation = saturation;
    }

    public TYPE getFileType() {
        return fileType;
    }

    public void setFileType(TYPE fileType) {
        this.fileType = fileType;
    }

    public double getFileQuality() {
        return fileQuality;
    }

    public void setFileQuality(double fileQuality) {
        this.fileQuality = fileQuality;
    }

    public COLOR_TYPE getColorType() {
        return colorType;
    }

    public void setColorType(COLOR_TYPE colorType) {
        this.colorType = colorType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataProcRequirement that = (DataProcRequirement) o;
        return Double.compare(that.fileQuality, fileQuality) == 0 &&
                Double.compare(that.contrast, contrast) == 0 &&
                Double.compare(that.brightness, brightness) == 0 &&
                Double.compare(that.saturation, saturation) == 0 &&
                fileType == that.fileType &&
                colorType == that.colorType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileType, fileQuality, colorType, contrast, brightness, saturation);
    }

    @Override
    public String toString() {
        return "DataProcRequirement{" +
                "fileType=" + fileType +
                ", fileQuality=" + fileQuality +
                ", colorType=" + colorType +
                ", contrast=" + contrast +
                ", brightness=" + brightness +
                ", saturation=" + saturation +
                '}';
    }
}
