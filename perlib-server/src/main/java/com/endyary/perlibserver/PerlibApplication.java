package com.endyary.perlibserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PerlibApplication {

    @RestController
    public static class WelcomeController {
        @RequestMapping(value = "/")
        public String sayWelcome() {
            return "Welcome to Perlib Application";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(PerlibApplication.class, args);
    }
}
