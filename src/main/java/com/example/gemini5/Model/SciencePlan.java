package com.example.gemini5.Model;

import edu.gemini.app.ocs.model.BaseSciencePlan;
import edu.gemini.app.ocs.model.DataProcRequirement;
import jparsec.ephem.Target;

import javax.persistence.*;
import java.util.Date;

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
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                ", date=" + date +
                ", telescopeLoc=" + telescopeLoc +
                ", dataProcessingReq='" + dataProcessingReq + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public BaseSciencePlan toBaseSciencePlan() {
        BaseSciencePlan temp = new BaseSciencePlan();
        temp.setPlanNo(this.getPlanId());
        temp.setCreator(this.getCreator());
        temp.setSubmitter(this.getSubmitter());
        temp.setFundingInUSD(this.getFunding());
        temp.setObjectives(this.getObjective());
        temp.setStarSystem(Target.TARGET.MARS);
        return temp;
    }
}
