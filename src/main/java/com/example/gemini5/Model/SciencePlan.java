package com.example.gemini5.Model;

import com.example.gemini5.Gemini5Application;
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

    public enum TESTRESULT {
        UNTEST, SUCCESS, FAILED
    }

    public enum APPROVERESULT {
        UNAPPROVE, APPROVED, REJECTED
    }

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

    @Column
    @Enumerated(value = EnumType.STRING)
    private TESTRESULT testresult = TESTRESULT.UNTEST;

    @Column
    private Date testdate;

    @Column
    @Enumerated(value = EnumType.STRING)
    private APPROVERESULT approveresult = APPROVERESULT.UNAPPROVE;

    @Column
    private String approver;

    @Column
    private String rejectnote;

    public String getRejectnote() {
        return rejectnote;
    }

    public void setRejectnote(String rejectnote) {
        this.rejectnote = rejectnote;
    }

    public TESTRESULT getTestresult() {
        return testresult;
    }

    public void setTestresult(TESTRESULT testresult) {
        this.testresult = testresult;
    }

    public Date getTestdate() {
        return testdate;
    }

    public void setTestdate(Date testdate) {
        this.testdate = testdate;
    }

    public APPROVERESULT getApproveresult() {
        return approveresult;
    }

    public void setApproveresult(APPROVERESULT approveresult) {
        this.approveresult = approveresult;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

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
                ", testresult='" + testresult + '\'' +
                ", testdate=" + testdate +
                ", approveresult='" + approveresult + '\'' +
                ", approver='" + approver + '\'' +
                '}';
    }

}
