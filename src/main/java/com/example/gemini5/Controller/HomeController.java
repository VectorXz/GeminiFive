package com.example.gemini5.Controller;

import com.example.gemini5.Repository.SciencePlanRepository;
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
