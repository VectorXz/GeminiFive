package com.example.gemini5.Controller;

import com.example.gemini5.Model.SciencePlan;
import com.example.gemini5.Repository.SciencePlanRepository;
import edu.gemini.app.ocs.controller.VirtualTelescopeHandler;
import edu.gemini.app.ocs.model.BaseSciencePlan;
import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.VirtualTelescope;
import jparsec.ephem.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private SciencePlanRepository sciPlanRepository;

    @RequestMapping(value = "/homeast", method = RequestMethod.GET)
    public String getHomeAst(Model model) {
        // return login.html
        model.addAttribute("allSciPlans", sciPlanRepository.findAll());
        return "homeast";
    }

    @RequestMapping(value = "/homesco", method = RequestMethod.GET)
    public String getHomeSco(Model model) {
        // return login.html
        model.addAttribute("allSciPlans", sciPlanRepository.findByStatus(BaseSciencePlan.STATUS.SUBMITTED));
        return "homesco";
    }

}
