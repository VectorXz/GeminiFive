package com.example.gemini5.Controller;

import com.example.gemini5.Model.AddForm;
import com.example.gemini5.Model.SciPlanForm;
import com.example.gemini5.Model.SciencePlan;
import com.example.gemini5.Model.User;
import com.example.gemini5.Repository.SciencePlanRepository;
import com.example.gemini5.Repository.UserRepository;
import edu.gemini.app.ocs.model.BaseSciencePlan;
import edu.gemini.app.ocs.model.DataProcRequirement;
import jparsec.ephem.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/addsciplan", method = RequestMethod.POST)
    public @ResponseBody
    String add(@ModelAttribute(name="SciPlanForm") SciPlanForm SciPlanForm, Model model) {
        String name = SciPlanForm.getName();
        double funding = SciPlanForm.getFunding();
        String objective = SciPlanForm.getObjective();
        Target.TARGET starsSystem = SciPlanForm.getStarsystem();
        String datestr = SciPlanForm.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Time time = SciPlanForm.getTime();
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
        tmp.setDate(date);
        tmp.setTelescopeLoc(location);
        String dataProcReq = filetype+","+quality+","+color+","+contrast+","+brightness+","+saturation;
        tmp.setDataProcessingReq(dataProcReq);
        tmp.setCreator(creator);
        tmp.setStatus(BaseSciencePlan.STATUS.COMPLETE);

        sciencePlanRepository.save(tmp);
        return "Added!";
    }
}
