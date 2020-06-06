/*
UNUSED FILE WAITING FOR DELETE
 */

package com.example.gemini5;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/homeast").setViewName("homeast");
        //registry.addViewController("/login").setViewName("login");
    }

}