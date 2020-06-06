package com.example.gemini5.Controller;

import com.example.gemini5.Model.AddForm;
import com.example.gemini5.Model.User;
import com.example.gemini5.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddForm() {
        // return login.html
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String add(@ModelAttribute(name="addForm") AddForm addForm, Model model) {
        String username = addForm.getUsername();
        String password = addForm.getPassword();
        String name = addForm.getName();
        String surname = addForm.getSurname();
        String email = addForm.getEmail();
        String phoneno = addForm.getPhoneno();
        String address = addForm.getAddress();
        String accesslevel = addForm.getAccesslevel();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        User tmp = new User();
        tmp.setUsername(username);
        tmp.setPassword(hashedPassword);
        tmp.setName(name);
        tmp.setSurname(surname);
        tmp.setEmail(email);
        tmp.setPhoneno(phoneno);
        tmp.setAddress(address);
        tmp.setAccesslevel(accesslevel);

        userRepository.save(tmp);
        return "Added!";
    }

}
