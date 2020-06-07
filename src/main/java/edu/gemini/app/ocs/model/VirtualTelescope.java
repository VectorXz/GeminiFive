/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package edu.gemini.app.ocs.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a class that represents the virtual telescope in the Gemini system.
 */
public class VirtualTelescope {
    private String name;
    private String location;
    private Date installedDate;
    private BaseSciencePlan plan = null;

    public static String NORTH = "Gemini North";
    public static String SOUTH = "Gemini South";

    /**
     * A constructor
     */
    private VirtualTelescope() {
        this.name = NORTH;
        this.location = "Mauna Kea, Hawaii";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.installedDate = format.parse( "2000-01-01" );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * A constructor with given parameters
     * @param name - the name of the virtual telescope
     * @param location - the location of the actual telescope that this virtual telescope represents
     * @param installedDate - the date the virtual telescope is installed
     */
    private VirtualTelescope(String name, String location, String installedDate) {
        this.name = name;
        this.location = location;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.installedDate = format.parse( installedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static VirtualTelescope vt = null;

    /**
     * A static method to get an instance of VirtualTelescope class.
     * This is implemented as a singleton class so only one active VirtualTelescope object exists.
     * @return an instance of a virtual telescope class
     */
    public static VirtualTelescope getInstance()
    {
        if (vt == null)
            vt = new VirtualTelescope();
        return vt;
    }

    /**
     * Assign a science plan to the virtual telscope
     * @param plan - a science plan, which can be a subclass of {@link BaseSciencePlan} class.
     */
    public void setSciencePlan(BaseSciencePlan plan) {
        this.plan = plan;
    }

    /**
     * Get the currently assigned science plan
     * @return the science plan
     */
    public BaseSciencePlan getSciencePlan() {
        return this.plan;
    }

    /**
     * Return the info of the currently assigned science plan
     * @return the string of the science plan info
     */
    public String showSciencePlan() {
        return this.plan.toString();
    }

    /**
     * Exception class when no science plan is assigned to the virtual telescope when executing it.
     */
    public class NoSciencePlanException extends Exception {
        public NoSciencePlanException(String message) {
            super(message);
        }
    }

    /**
     * Execute the given science plan
     * @return true if successfully executed the science plan, false if failed to execute the plan
     */
    public boolean executeSciencePlan() throws NoSciencePlanException {
        if (plan == null) {
            throw new NoSciencePlanException("No science plan assigned.");
        }
        double threshold = 90;
        // a fake code to generate successful results with 90% probability
        double d = Math.random() * 100;
        if (d <= threshold) {
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    /**
     * Print out name, location and installed date.
     * @return A string representation of the class
     */
    public String toString() {
        return this.name + ", " + this.location + "\nInstalled on: " + this.installedDate.toString();
    }
}
