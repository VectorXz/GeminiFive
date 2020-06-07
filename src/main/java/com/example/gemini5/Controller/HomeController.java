package com.example.gemini5.Controller;

import com.example.gemini5.Model.SciencePlan;
import com.example.gemini5.Repository.SciencePlanRepository;
import edu.gemini.app.ocs.controller.VirtualTelescopeHandler;
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

    // Mapping for science observer
    @GetMapping("/homesco/review")
    public String processForm() {
        return "reviewSubmitSci";
    }

    @RequestMapping(value = "/homeast", method = RequestMethod.GET)
    public String getHomeAst(Model model) {
        // return login.html
        model.addAttribute("allSciPlans", sciPlanRepository.findAll());
        return "homeast";
    }

    @RequestMapping(value = "/testsciplan", method = RequestMethod.GET)
    public String getTestSciPlan(Model model) {
        // return login.html
        model.addAttribute("allSciPlans", sciPlanRepository.findAll());
        return "testsciplan";
    }

    @RequestMapping(value = "/testsciplan/{id}", method = RequestMethod.GET)
    public String getTestSciPlanById(@PathVariable("id") Integer id,Model model) {
        // return login.html
        SciencePlan selected = sciPlanRepository.findByPlanId(id);
        model.addAttribute("plan", selected);
        VirtualTelescope vt = VirtualTelescopeHandler.getVirtualTelescope(VirtualTelescope.NORTH);
        System.out.println(vt);
        return "testsciplanID";
    }

    @RequestMapping(value = "/createsciplan", method = RequestMethod.GET)
    public String getCreateSciPlan(Model model) {
        // return login.html
        model.addAttribute("starsSystem", Target.TARGET.values());
        return "createsciplan";
    }

    @RequestMapping(value = "/homesco", method = RequestMethod.GET)
    public String getHomeSco(Model model) {
        // return login.html
        model.addAttribute("allSciPlans", sciPlanRepository.findByStatus("submitted"));
        return "homesco";
    }

    @GetMapping("/viewsciplan/{id}")
    public String getSciencePlanById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("plan", sciPlanRepository.findByPlanId(id));
        return "viewsciplan";
    }

}
