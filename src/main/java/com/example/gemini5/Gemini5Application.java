package com.example.gemini5;

import edu.gemini.app.ocs.OCS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Gemini5Application {

    public static OCS mainOCS = new OCS();

    public static void main(String[] args) {
        SpringApplication.run(Gemini5Application.class, args);
    }
}
