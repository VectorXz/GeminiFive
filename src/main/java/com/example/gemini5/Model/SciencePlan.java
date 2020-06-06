package com.example.gemini5.Model;

import javax.persistence.*;

@Entity
@Table(name = "SciencePlan")
public class SciencePlan {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int planId;

    @Column
    private String creator;

    @Column
    private String submitter;

    @Column
    private float funding;

    @Column
    private String objective;

    @Column
    private String starsSystem;

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

    public String getStarsSystem() {
        return starsSystem;
    }

    public void setStarsSystem(String starsSystem) {
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
}
