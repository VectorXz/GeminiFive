package com.example.gemini5.Model;

import edu.gemini.app.ocs.model.BaseSciencePlan;
import jparsec.ephem.Target;

import javax.persistence.*;

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
    private float funding;

    @Column
    private String objective;

    @Column
    private Target.TARGET starsSystem;

    @Column
    private String schedule;

    @Column
    private String telescopeLoc;

    @Column
    private String dataProcessingReq;

    @Column
    private String comment;

    @Column
    private String status;

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

    public float getFunding() {
        return funding;
    }

    public void setFunding(float funding) {
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTelescopeLoc() {
        return telescopeLoc;
    }

    public void setTelescopeLoc(String telescopeLoc) {
        this.telescopeLoc = telescopeLoc;
    }

    public String getDataProcessingReq() {
        return dataProcessingReq;
    }

    public void setDataProcessingReq(String dataProcessingReq) {
        this.dataProcessingReq = dataProcessingReq;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SciencePlan{" +
                "planId=" + planId +
                ", creator='" + creator + '\'' +
                ", submitter='" + submitter + '\'' +
                ", funding=" + funding +
                ", objective='" + objective + '\'' +
                ", starsSystem='" + starsSystem + '\'' +
                ", schedule='" + schedule + '\'' +
                ", telescopeLoc='" + telescopeLoc + '\'' +
                ", dataProcessingReq='" + dataProcessingReq + '\'' +
                ", comment='" + comment + '\'' +
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
