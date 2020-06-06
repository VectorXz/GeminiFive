package com.example.gemini5.Controller;

import com.example.gemini5.LoginForm;
import com.example.gemini5.Model.SciencePlan;
import com.example.gemini5.SciencePlanRepository;
import com.example.gemini5.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SciencePlanRepository sciPlanRepository;


    //to get login form page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        // return login.html
        return "login";
    }

    @RequestMapping(value = "/homeast", method = RequestMethod.GET)
    public String getHomeAst(Model model) {
        // return login.html
        model.addAttribute("allSciPlans", sciPlanRepository.findAll());
        return "homeast";
    }

    @RequestMapping(value = "/homesco", method = RequestMethod.GET)
    public String getHomeSco() {
        // return login.html
        return "homesco";
    }

    @RequestMapping(value="/all", method=RequestMethod.GET)
    public @ResponseBody Iterable<SciencePlan> getAllSciPlan() {
        return sciPlanRepository.findAll();
    }

    /*/checking for login credentials
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name="loginForm") LoginForm loginForm, Model model) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if(!userRepository.findByUsername(username).isEmpty()) {
            String dbusername = userRepository.findByUsername(username).get(0).getUsername();
            String dbpassword = userRepository.findByUsername(username).get(0).getPassword();
            if(dbusername.equals(username) && dbpassword.equals(password)) {
                return "homeast";
            } else {
                model.addAttribute("invalidPassword", true);
            }
        } else {
            model.addAttribute("userNotFound", true);
        }
        return "login";
    }
    */
}
