package com.example.gemini5.Model;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.*;
import jparsec.ephem.Target;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "SciencePlan")
public class SciencePlan {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int planId;

    @Column
    private String planName;

    @Column
    private String creator;

    @Column
    private String submitter;

    @Column
    private double funding;

    @Column
    private String objective;

    @Column
    private Target.TARGET starsSystem;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private BaseSciencePlan.TELESCOPELOC telescopeLoc;

    @Column
    private String dataProcessingReq;

    @Column
    private BaseSciencePlan.STATUS status;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }


    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
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

    public Target.TARGET getStarsSystem() {
        return starsSystem;
    }

    public void setStarsSystem(Target.TARGET starsSystem) {
        this.starsSystem = starsSystem;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BaseSciencePlan.TELESCOPELOC getTelescopeLoc() {
        return telescopeLoc;
    }

    public void setTelescopeLoc(BaseSciencePlan.TELESCOPELOC telescopeLoc) {
        this.telescopeLoc = telescopeLoc;
    }

    public String getDataProcessingReq() {
        return dataProcessingReq;
    }

    public void setDataProcessingReq(String dataProcessingReq) {
        this.dataProcessingReq = dataProcessingReq;
    }

    public BaseSciencePlan.STATUS getStatus() {
        return status;
    }

    public void setStatus(BaseSciencePlan.STATUS status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SciencePlan{" +
                "planId=" + planId +
                ", planName='" + planName + '\'' +
                ", creator='" + creator + '\'' +
                ", submitter='" + submitter + '\'' +
                ", funding=" + funding +
                ", objective='" + objective + '\'' +
                ", starsSystem=" + starsSystem +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", telescopeLoc=" + telescopeLoc +
                ", dataProcessingReq='" + dataProcessingReq + '\'' +
                ", status=" + status +
                '}';
    }

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

    public BaseSciencePlan toBaseSciencePlan() {
        BaseSciencePlan temp = new BaseSciencePlan();
        temp.setPlanNo(this.getPlanId());
        temp.setCreator(this.getCreator());
        temp.setSubmitter(this.getSubmitter());
        temp.setFundingInUSD(this.getFunding());
        temp.setObjectives(this.getObjective());
        temp.setStarSystem(this.getStarsSystem());
        temp.setStartDate(this.getStartDate());
        temp.setEndDate(this.getEndDate());
        temp.setTelescopeLocation(this.getTelescopeLoc());
        /* REQ PROCESS CONVERT */
        String[] data = this.getDataProcessingReq().split(",");
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
        cal.setTime(this.getStartDate());

        /* GENERATE BASE OBSERVING PROGRAM */
        OCS ocs = new OCS();
        BaseObservingProgram bop = new BaseObservingProgram();
        bop.setId(this.getPlanId());
        bop.setLoc(ocs.getLocation(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), this.getStarsSystem()));
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

        return temp;
    }
}
