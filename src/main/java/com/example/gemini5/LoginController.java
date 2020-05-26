package com.example.gemini5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;


    //to get login form page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
        // return login.html
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome() {
        // return login.html
        return "home";
    }

    //checking for login credentials
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name="loginForm") LoginForm loginForm, Model model) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if(!userRepository.findByUsername(username).isEmpty()) {
            String dbusername = userRepository.findByUsername(username).get(0).getUsername();
            String dbpassword = userRepository.findByUsername(username).get(0).getPassword();
            if(dbusername.equals(username) && dbpassword.equals(password)) {
                return "home";
            } else {
                model.addAttribute("invalidPassword", true);
            }
        } else {
            model.addAttribute("userNotFound", true);
        }
        return "login";
    }
}
