package com.example.gemini5.Controller;

import com.example.gemini5.Gemini5Application;
import com.example.gemini5.Model.AddForm;
import com.example.gemini5.Model.SciPlanForm;
import com.example.gemini5.Model.SciencePlan;
import com.example.gemini5.Model.User;
import com.example.gemini5.Repository.SciencePlanRepository;
import com.example.gemini5.Repository.UserRepository;
import edu.gemini.app.ocs.controller.VirtualTelescopeHandler;
import edu.gemini.app.ocs.model.BaseSciencePlan;
import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.VirtualTelescope;
import jparsec.ephem.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class SciencePlanController {
    @Autowired
    private SciencePlanRepository sciencePlanRepository;

    @RequestMapping(value = "/createsciplan", method = RequestMethod.GET)
    public String getCreateSciPlan(Model model) {
        // return login.html
        model.addAttribute("starsSystem", Target.TARGET.values());
        model.addAttribute("color", DataProcRequirement.COLOR_TYPE.values());
        model.addAttribute("location", BaseSciencePlan.TELESCOPELOC.values());
        return "createsciplan";
    }

    @GetMapping("/viewsciplan/{id}")
    public String getSciencePlanById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("plan", sciencePlanRepository.findByPlanId(id));
        String[] data = sciencePlanRepository.findByPlanId(id).getDataProcessingReq().split(",");
        model.addAttribute("filetype", data[0]);
        model.addAttribute("filequality", data[1]);
        model.addAttribute("filecolor", data[2]);
        model.addAttribute("filecontrast", data[3]);
        model.addAttribute("filebrightness", data[4]);
        model.addAttribute("filesaturation", data[5]);
        return "viewsciplan";
    }

    // Mapping for science observer
    @GetMapping("/reviewsciplan/{id}")
    public String getReviewSciPlan(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("plan", sciencePlanRepository.findByPlanId(id));
        String[] data = sciencePlanRepository.findByPlanId(id).getDataProcessingReq().split(",");
        model.addAttribute("filetype", data[0]);
        model.addAttribute("filequality", data[1]);
        model.addAttribute("filecolor", data[2]);
        model.addAttribute("filecontrast", data[3]);
        model.addAttribute("filebrightness", data[4]);
        model.addAttribute("filesaturation", data[5]);
        return "reviewSubmitSci";
    }

    @GetMapping("/approvesciplan/{id}")
    public String getApproveSciPlan(@PathVariable("id") Integer id, Model model) {
        SciencePlan selected = sciencePlanRepository.findByPlanId(id);
        selected.setApproveresult(SciencePlan.APPROVERESULT.APPROVED);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        selected.setApprover(username);
        if(sciencePlanRepository.save(selected) != null) {
            model.addAttribute("result", "success");
            model.addAttribute("plan", selected);
        } else {
            model.addAttribute("result", "failed");
            model.addAttribute("plan", selected);
        }
        return "approvesciplan";
    }

    @GetMapping("/rejectsciplan/{id}")
    public String getRejectSciPlan(@PathVariable("id") Integer id, Model model) {
        SciencePlan selected = sciencePlanRepository.findByPlanId(id);
        selected.setApproveresult(SciencePlan.APPROVERESULT.REJECTED);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        selected.setApprover(username);
        if(sciencePlanRepository.save(selected) != null) {
            model.addAttribute("result", "success");
            model.addAttribute("plan", selected);
        } else {
            model.addAttribute("result", "failed");
            model.addAttribute("plan", selected);
        }
        return "rejectsciplan";
    }

    // TODO
    @GetMapping("/editsciplan/{id}")
    public String getEditSciPlanById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("starsSystem", Target.TARGET.values());
        model.addAttribute("color", DataProcRequirement.COLOR_TYPE.values());
        model.addAttribute("location", BaseSciencePlan.TELESCOPELOC.values());
        model.addAttribute("plan", sciencePlanRepository.findByPlanId(id));
        String startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(sciencePlanRepository.findByPlanId(id).getStartDate());
        String endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(sciencePlanRepository.findByPlanId(id).getEndDate());
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        String[] data = sciencePlanRepository.findByPlanId(id).getDataProcessingReq().split(",");
        model.addAttribute("filetype", data[0]);
        model.addAttribute("filequality", data[1]);
        model.addAttribute("filecolor", data[2]);
        model.addAttribute("filecontrast", data[3]);
        model.addAttribute("filebrightness", data[4]);
        model.addAttribute("filesaturation", data[5]);
        return "editsciplan";
    }

    @RequestMapping(value = "/savesciplan/{id}", method = RequestMethod.POST)
    public String save(@PathVariable("id") Integer id, @ModelAttribute(name="SciPlanForm") SciPlanForm SciPlanForm, Model model) {
        String name = SciPlanForm.getName();
        double funding = SciPlanForm.getFunding();
        String objective = SciPlanForm.getObjective();
        Target.TARGET starsSystem = SciPlanForm.getStarsystem();
        String startdatestr = SciPlanForm.getStartdate();
        String enddatestr = SciPlanForm.getEnddate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date startdate = null;
        Date enddate = null;
        try {
            startdate = df.parse(startdatestr);
            enddate = df.parse(enddatestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseSciencePlan.TELESCOPELOC location = SciPlanForm.getLocation();
        DataProcRequirement.TYPE filetype = SciPlanForm.getFiletype();
        double quality = SciPlanForm.getQuality();
        DataProcRequirement.COLOR_TYPE color = SciPlanForm.getColor();
        double contrast = SciPlanForm.getContrast();
        double brightness = SciPlanForm.getBrightness();
        double saturation = SciPlanForm.getSaturation();
        String creator = SciPlanForm.getCreator();

        SciencePlan tmp = new SciencePlan();
        tmp.setPlanId(id);
        tmp.setPlanName(name);
        tmp.setFunding(funding);
        tmp.setObjective(objective);
        tmp.setStarsSystem(starsSystem);
        tmp.setStartDate(startdate);
        tmp.setEndDate(enddate);
        tmp.setTelescopeLoc(location);
        String dataProcReq = filetype+","+quality+","+color+","+contrast+","+brightness+","+saturation;
        tmp.setDataProcessingReq(dataProcReq);
        tmp.setCreator(creator);
        tmp.setStatus(BaseSciencePlan.STATUS.COMPLETE);
        tmp.setApproveresult(SciencePlan.APPROVERESULT.UNAPPROVE);
        tmp.setApprover(null);
        tmp.setTestresult(SciencePlan.TESTRESULT.UNTEST);
        tmp.setTestdate(null);
        tmp.setCreator(creator);

        // add model to show the SciencePlan in view
        model.addAttribute("plan", tmp);

        // Save science plan in the repository
        if(sciencePlanRepository.save(tmp) != null) {
            model.addAttribute("result", "success");
        } else {
            model.addAttribute("result", "failed");
        }
        return "saveresult";
    }

    @GetMapping("/submitsciplan/{id}")
    public String getSubmitSciPlan(@PathVariable("id") Integer id, Model model) {
        SciencePlan selected = sciencePlanRepository.findByPlanId(id);
        selected.setStatus(BaseSciencePlan.STATUS.SUBMITTED);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        selected.setSubmitter(username);
        Gemini5Application.mainOCS.submitSciencePlan(selected.toBaseSciencePlan());
        if(sciencePlanRepository.save(selected) != null) {
            model.addAttribute("result", "success");
            model.addAttribute("plan", selected);
        } else {
            model.addAttribute("result", "failed");
            model.addAttribute("plan", selected);
        }
        return "submitsuccess";
    }

    @RequestMapping(value = "/testsciplan", method = RequestMethod.GET)
    public String getTestSciPlan(Model model) {
        // return login.html
        model.addAttribute("allSciPlans", sciencePlanRepository.findAll());
        return "testsciplan";
    }

    @RequestMapping(value = "/testsciplan/{id}", method = RequestMethod.GET)
    public String getTestSciPlanById(@PathVariable("id") Integer id, Model model) {
        // return login.html
        SciencePlan selected = sciencePlanRepository.findByPlanId(id);
        model.addAttribute("plan", selected);
        String[] data = selected.getDataProcessingReq().split(",");
        model.addAttribute("filetype", data[0]);
        model.addAttribute("filequality", data[1]);
        model.addAttribute("filecolor", data[2]);
        model.addAttribute("filecontrast", data[3]);
        model.addAttribute("filebrightness", data[4]);
        model.addAttribute("filesaturation", data[5]);
        VirtualTelescope vt = VirtualTelescopeHandler.getVirtualTelescope(VirtualTelescope.NORTH);
        model.addAttribute("vt", vt);
        return "testsciplanID";
    }

    @RequestMapping(value = "/testresult/{id}", method = RequestMethod.GET)
    public String getTestResult(@PathVariable("id") Integer id, Model model) {
        // return login.html
        SciencePlan selected = sciencePlanRepository.findByPlanId(id);
        model.addAttribute("sciPlan", selected);
        VirtualTelescope vt = VirtualTelescopeHandler.getVirtualTelescope(VirtualTelescope.NORTH);
        vt.setSciencePlan(selected.toBaseSciencePlan());
        try {
            boolean success = vt.executeSciencePlan();
            model.addAttribute("result", success);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatter.format(date));
            selected.setTestdate(d);
            if(success) {
                selected.setTestresult(SciencePlan.TESTRESULT.SUCCESS);
            } else if(!success) {
                selected.setTestresult(SciencePlan.TESTRESULT.FAILED);
            } else {
                selected.setTestresult(SciencePlan.TESTRESULT.UNTEST);
            }
            sciencePlanRepository.save(selected);
        } catch (VirtualTelescope.NoSciencePlanException | ParseException e) {
            e.printStackTrace();
        }
        return "submitScienceplan";
    }

    @RequestMapping(value = "/addsciplan", method = RequestMethod.POST)
    public String add(@ModelAttribute(name="SciPlanForm") SciPlanForm SciPlanForm, Model model) {
        String name = SciPlanForm.getName();
        double funding = SciPlanForm.getFunding();
        String objective = SciPlanForm.getObjective();
        Target.TARGET starsSystem = SciPlanForm.getStarsystem();
        String startdatestr = SciPlanForm.getStartdate();
        String enddatestr = SciPlanForm.getEnddate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date startdate = null;
        Date enddate = null;
        try {
            startdate = df.parse(startdatestr);
            enddate = df.parse(enddatestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        BaseSciencePlan.TELESCOPELOC location = SciPlanForm.getLocation();
        DataProcRequirement.TYPE filetype = SciPlanForm.getFiletype();
        double quality = SciPlanForm.getQuality();
        DataProcRequirement.COLOR_TYPE color = SciPlanForm.getColor();
        double contrast = SciPlanForm.getContrast();
        double brightness = SciPlanForm.getBrightness();
        double saturation = SciPlanForm.getSaturation();
        String creator = SciPlanForm.getCreator();

        SciencePlan tmp = new SciencePlan();
        tmp.setPlanName(name);
        tmp.setFunding(funding);
        tmp.setObjective(objective);
        tmp.setStarsSystem(starsSystem);
        tmp.setStartDate(startdate);
        tmp.setEndDate(enddate);
        tmp.setTelescopeLoc(location);
        String dataProcReq = filetype+","+quality+","+color+","+contrast+","+brightness+","+saturation;
        tmp.setDataProcessingReq(dataProcReq);
        tmp.setCreator(creator);
        tmp.setStatus(BaseSciencePlan.STATUS.COMPLETE);

        // add model to show the SciencePlan in view
        model.addAttribute("plan", tmp);

        // Save science plan in the repository
        sciencePlanRepository.save(tmp);
        return "createsuccess";
    }
}
