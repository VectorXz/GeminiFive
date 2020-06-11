/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package edu.gemini.app.ocs;

import edu.gemini.app.ocs.model.*;
import jparsec.astronomy.CoordinateSystem;
import jparsec.ephem.Ephem;
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.Target;
import jparsec.ephem.planets.EphemElement;
import jparsec.observer.CityElement;
import jparsec.observer.LocationElement;
import jparsec.observer.ObserverElement;
import jparsec.time.AstroDate;
import jparsec.time.TimeElement;
import jparsec.util.JPARSECException;

import java.util.ArrayList;

public class OCS {
    private static ArrayList<BaseSciencePlan> sciencePlans = new ArrayList<>();;

    public OCS() {
        populateSciencePlans();
    }

    public OCS(boolean createMockData) {
        if( createMockData ) {
            populateSciencePlans();
        }
    }

    private void populateSciencePlans() {
        // create a dummy science plans and add to the list
        BaseSciencePlan sp1 = new BaseSciencePlan();
        sp1.setPlanNo(1);
        sp1.setCreator("John Doe");
        sp1.setSubmitter("John Doe");
        sp1.setFundingInUSD(1000);
        sp1.setObjectives("1. To explore Neptune.\n2. To collect astronomical data for future research.");
        sp1.setStarSystem(Target.TARGET.NEPTUNE);
        sp1.setStartDate("09/04/2020");
        sp1.setTelescopeLocation(BaseSciencePlan.TELESCOPELOC.HAWAII);
        sp1.setEndDate("15/04/2020");
        BaseObservingProgram op1 = new BaseObservingProgram();
        op1.setId(1);
        op1.setLoc(getLocation(2020, 4, 9, Target.TARGET.NEPTUNE));
        op1.setLens(new Lens("Canon", "DX-300", "Canon Inc.", 2018));
        Filter f1 =
                new Filter("Canon", "Canon Inc.", "RF-200", 2017, 5, 2.5);
        ArrayList<Filter> filters1 = new ArrayList<Filter>();
        filters1.add(f1);
        op1.setFilters(filters1);
        ArrayList<Double> exp1 = new ArrayList<>();
        exp1.add(0.25);
        op1.setExposures(exp1);
        op1.setLightDetectorOn(false);
        op1.setSpecialEquipments(null);
        AstronomicalData data1 = new AstronomicalData();
        op1.setAstroData(data1);
        sp1.setObservingProgram(op1);
        sciencePlans.add(sp1);

        BaseSciencePlan sp2 = new BaseSciencePlan();
        sp2.setPlanNo(2);
        sp2.setCreator("Jane Dunn");
        sp2.setSubmitter("Andrew Griffin");
        sp2.setFundingInUSD(2500);
        sp2.setObjectives("1. To explore Mars.\n2. To collect astronomical data for future research.");
        sp2.setStarSystem(Target.TARGET.MARS);
        sp2.setStartDate("15/05/2020");
        sp2.setTelescopeLocation(BaseSciencePlan.TELESCOPELOC.CHILE);
        sp2.setEndDate("15/06/2020");
        BaseObservingProgram op2 = new BaseObservingProgram();
        op2.setId(2);
        op2.setLoc(getLocation(2020, 5, 15, Target.TARGET.MARS));
        op2.setLens(new Lens("Canon", "DX-500", "Canon Inc.", 2019));
        Filter f2 =
                new Filter("Nikon", "Nikon Inc.", "NI-450", 2018, 6, 5.2);
        ArrayList<Filter> filters2 = new ArrayList<Filter>();
        filters2.add(f2);
        op2.setFilters(filters2);
        ArrayList<Double> exp2 = new ArrayList<>();
        exp2.add(0.15);
        op2.setExposures(exp2);
        op2.setLightDetectorOn(false);
        op2.setSpecialEquipments(null);
        AstronomicalData data2 = new AstronomicalData();
        op2.setAstroData(data2);
        sp1.setObservingProgram(op2);
        sciencePlans.add(sp2);
    }

    public LocationElement getLocation(int year, int month, int day, Target.TARGET target) {
        /* Code example is adapted from EphemTest1.java by Alonso Albi */
        try {
            // We need three objects: TimeElement, ObserverElement, and EphemerisElement
            AstroDate astro = new AstroDate(year, month, day); // or Constant.J2000, or empty for current date/time, ...
            TimeElement time = new TimeElement(astro, TimeElement.SCALE.UNIVERSAL_TIME_UTC);
            CityElement city = new CityElement("Bangkok");
            ObserverElement obs = new ObserverElement(city);

            // The ephemeris object defines the target body and how to calculate ephemeris. The algorithm
            // is set to Moshier, which is the best way for general calculations
            EphemerisElement eph = new EphemerisElement(target, EphemerisElement.COORDINATES_TYPE.APPARENT,
                    EphemerisElement.EQUINOX_OF_DATE, EphemerisElement.TOPOCENTRIC, EphemerisElement.REDUCTION_METHOD.IAU_2006,
                    EphemerisElement.FRAME.DYNAMICAL_EQUINOX_J2000, EphemerisElement.ALGORITHM.MOSHIER);
            EphemElement ee = Ephem.getEphemeris(time, obs, eph, true); // Compute also rise/set/transit
            LocationElement gal = CoordinateSystem.equatorialToGalactic(ee.getEquatorialLocation(), time, obs, eph);
            return gal;
        } catch (JPARSECException e)
        {
            e.showException();
        }
        return null;
    }

    public ArrayList<BaseSciencePlan> getAllSciencePlans() {
        return sciencePlans;
    }

    public BaseSciencePlan getSciencePlanByNo(int planNo) {
        for (BaseSciencePlan sp: sciencePlans) {
            if (sp.getPlanNo() == planNo) {
                return sp;
            }
        }
        return null;
    }

    public boolean submitSciencePlan(BaseSciencePlan sp) {
        sp.setStatus(BaseSciencePlan.STATUS.SUBMITTED);
        this.getAllSciencePlans().add(sp);
        return true;
    }
}
