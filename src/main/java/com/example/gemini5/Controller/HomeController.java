package com.example.gemini5.Controller;

import com.example.gemini5.Model.SciencePlan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    // Mapping for science observer
    @GetMapping("/homesco/review")
    public String processForm() {
        return "reviewSubmitSci";
    }

    // Mapping for astronomer
    @GetMapping("/homeast")
    public String CreatePlanForm(Model model) {
        model.addAttribute("plan", new SciencePlan());
        return "homeast";
    }

    @PostMapping("/homeast")
    public String CreatePlanSubmit(@ModelAttribute SciencePlan plan) {
        return "summary";
    }
}
