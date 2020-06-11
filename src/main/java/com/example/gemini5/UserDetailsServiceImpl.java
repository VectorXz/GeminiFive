package com.example.gemini5;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import com.example.gemini5.Model.User;
import com.example.gemini5.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!userRepository.findByUsername(username).isEmpty()) {
            String dbusername = userRepository.findByUsername(username).get(0).getUsername();
            String dbpassword = userRepository.findByUsername(username).get(0).getPassword();
            String accessLevel = userRepository.findByUsername(username).get(0).getAccesslevel();

            List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
            GrantedAuthority authority = new SimpleGrantedAuthority(accessLevel);
            grantList.add(authority);
            UserDetails user = new User(dbusername, dbpassword, grantList);
            return user;
        } else {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

    }

    private List<GrantedAuthority> getAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

}