package com.example.gemini5.Controller;

import com.example.gemini5.Model.SciencePlan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/homesco/review")
    public String processForm() {
        return "reviewSubmitSci";
    }
}
