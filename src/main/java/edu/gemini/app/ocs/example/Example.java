/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package edu.gemini.app.ocs.example;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.controller.VirtualTelescopeHandler;
import edu.gemini.app.ocs.example.MyObservingProgram;
import edu.gemini.app.ocs.example.MySciencePlan;
import edu.gemini.app.ocs.model.*;
import jparsec.ephem.Target;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Example {
    public static void main(String[] args) {
        /* 1. Example code on how to get the virtual telescope and test science plans on it */
        VirtualTelescope vt = VirtualTelescopeHandler.getVirtualTelescope(VirtualTelescope.NORTH);
        System.out.println(vt);
        // Get science plans submitted in the system
        OCS ocs = new OCS();
        ArrayList<BaseSciencePlan> sciencePlans = ocs.getAllSciencePlans();
        for (BaseSciencePlan sp: sciencePlans) {
            System.out.println(sp);
            // Test it on the virtual telescope
            vt.setSciencePlan(sp);
            try {
                boolean success = vt.executeSciencePlan();
                if (success) {
                    System.out.println("Science plan execution on the virtual telescope is successful.");
                } else {
                    System.out.println("Science plan execution on the virtual telescope is NOT successful.");
                }
            } catch (VirtualTelescope.NoSciencePlanException e) {
                e.printStackTrace();
            }
        }

        /* 2. Example code to submit a science plan to OCS.
        I created MySciencePlan that inherits from the OCS BaseSciencePlan and
        MyObservingProgram that inherits from the OCS BaseObservingProgram.
         */
        MySciencePlan mySP = new MySciencePlan();
        mySP.setPlanNo(3);
        mySP.setCreator("Morakot Choetkiertikul");
        mySP.setSubmitter("Chaiyong Ragkhitwetsagul");
        mySP.setFundingInUSD(2500);
        mySP.setObjectives("1. To explore Mars.\n2. To collect astronomical data for future research.");
        mySP.setStarSystem(Target.TARGET.MARS);
        mySP.setStartDate("01/04/2020");
        mySP.setTelescopeLocation(BaseSciencePlan.TELESCOPELOC.CHILE);
        mySP.setEndDate("19/04/2020");
        MyObservingProgram myOP = new MyObservingProgram();
        myOP.setId(1);
        myOP.setLoc(ocs.getLocation(2020, 4, 9, Target.TARGET.NEPTUNE));
        myOP.setLens(new Lens("Canon", "DX-300", "Canon Inc.", 2018));
        Filter f1 =
                new Filter("Canon", "Canon Inc.", "RF-200", 2017, 5, 2.5);
        ArrayList<Filter> filters1 = new ArrayList<Filter>();
        filters1.add(f1);
        myOP.setFilters(filters1);
        ArrayList<Double> exp1 = new ArrayList<>();
        exp1.add(0.25);
        myOP.setExposures(exp1);
        myOP.setLightDetectorOn(false);
        myOP.setSpecialEquipments(null);
        AstronomicalData data1 = new AstronomicalData();
        myOP.setAstroData(data1);
        mySP.setObservingProgram(myOP);
        // submit it to the OCS system
        ocs.submitSciencePlan(mySP);

        /* 3. Example code on how to get astronomical data */
        MySciencePlan myPlan = (MySciencePlan) ocs.getSciencePlanByNo(3);
        /* to be able to go through the next step, I need to manually change the
        status to COMPLETE. The real system will set this one according to
        its schedule and execution. */
        myPlan.setStatus(BaseSciencePlan.STATUS.COMPLETE);
        // check the status first. It must be COMPLETE in order to get the astro data
        if (myPlan.getStatus() == BaseSciencePlan.STATUS.COMPLETE) {
            AstronomicalData astroData = myPlan.getObservingProgram().getAstroData();
            try {
                ArrayList<BufferedImage> images = astroData.getAstronomicalData();
                for (BufferedImage img : images) {
                    JFrame frame = new JFrame();
                    frame.getContentPane().setLayout(new FlowLayout());
                    frame.getContentPane().add(new JLabel(new ImageIcon(img)));
                    frame.pack();
                    frame.setVisible(true);
                    // if you want the X button to close the app
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                System.out.println("Images = " + images.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
