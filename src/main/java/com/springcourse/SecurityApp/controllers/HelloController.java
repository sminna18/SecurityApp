package com.springcourse.SecurityApp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.springcourse.SecurityApp.security.PersonDetails;

@Controller
public class HelloController {
    @GetMapping("/myProfile")
    public String sayHello() {
        return "myProfile";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return "myProfile";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
