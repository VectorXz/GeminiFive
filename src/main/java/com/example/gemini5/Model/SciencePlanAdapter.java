package com.example.gemini5.Model;

import com.example.gemini5.Gemini5Application;
import com.example.gemini5.PlanCreator;
import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class SciencePlanAdapter implements PlanCreator {
    SciencePlan scp;

    public DataProcRequirement.TYPE typeConverter(String type) {
        if(type == "JPEG") {
            return DataProcRequirement.TYPE.JPEG;
        } else if (type == "RAW") {
            return DataProcRequirement.TYPE.RAW;
        } else if (type == "PNG") {
            return DataProcRequirement.TYPE.PNG;
        } else if (type == "TIFF") {
            return DataProcRequirement.TYPE.TIFF;
        } else {
            return DataProcRequirement.TYPE.JPEG;
        }
    }

    public DataProcRequirement.COLOR_TYPE colorConverter(String color) {
        if(color == "COLOR") {
            return DataProcRequirement.COLOR_TYPE.COLOR;
        } else {
            return DataProcRequirement.COLOR_TYPE.BW;
        }
    }

    public BaseSciencePlan toBaseSciencePlan(SciencePlan sciencePlan) {
        System.out.println("Converting...");
        BaseSciencePlan temp = new BaseSciencePlan();
        temp.setPlanNo(sciencePlan.getPlanId());
        temp.setCreator(sciencePlan.getCreator());
        temp.setSubmitter(sciencePlan.getSubmitter());
        temp.setFundingInUSD(sciencePlan.getFunding());
        temp.setObjectives(sciencePlan.getObjective());
        temp.setStarSystem(sciencePlan.getStarsSystem());
        temp.setStartDate(sciencePlan.getStartDate());
        temp.setEndDate(sciencePlan.getEndDate());
        temp.setTelescopeLocation(sciencePlan.getTelescopeLoc());
        /* REQ PROCESS CONVERT */
        String[] data = sciencePlan.getDataProcessingReq().split(",");
        DataProcRequirement req = new DataProcRequirement();
        req.setFileType(typeConverter(data[0]));
        req.setFileQuality(Double.parseDouble(data[1]));
        req.setColorType(colorConverter(data[2]));
        req.setContrast(Double.parseDouble(data[3]));
        req.setBrightness(Double.parseDouble(data[4]));
        req.setSaturation(Double.parseDouble(data[5]));
        ArrayList<DataProcRequirement> reqList = new ArrayList<DataProcRequirement>();
        reqList.add(req);
        temp.setDataProcRequirements(reqList);

        /* Extract Date */
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Bangkok"));
        cal.setTime(sciencePlan.getStartDate());

        /* GENERATE BASE OBSERVING PROGRAM */
        OCS ocs = Gemini5Application.mainOCS;
        BaseObservingProgram bop = new BaseObservingProgram();
        bop.setId(sciencePlan.getPlanId());
        bop.setLoc(ocs.getLocation(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), sciencePlan.getStarsSystem()));
        bop.setLens(new Lens("Canon", "DX-300", "Canon Inc.", 2018));
        Filter f1 =  new Filter("Canon", "Canon Inc.", "RF-200", 2017, 5, 2.5);
        ArrayList<Filter> filters1 = new ArrayList<Filter>();
        filters1.add(f1);
        bop.setFilters(filters1);
        ArrayList<Double> exp1 = new ArrayList<>();
        exp1.add(0.25);
        bop.setExposures(exp1);
        bop.setLightDetectorOn(false);
        bop.setSpecialEquipments(null);
        AstronomicalData data1 = new AstronomicalData();
        bop.setAstroData(data1);

        temp.setObservingProgram(bop);

        if(temp == null) {
            System.out.println("null");
        } else {
            System.out.println(temp);
        }
        System.out.println("Sending back...");
        return temp;
    }
}
