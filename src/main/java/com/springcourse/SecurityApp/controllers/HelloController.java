package com.springcourse.SecurityApp.controllers;

import com.springcourse.SecurityApp.models.Person;
import com.springcourse.SecurityApp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.springcourse.SecurityApp.security.PersonDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class HelloController {
    @Autowired
    PersonDetailsService personDetailsService;

    @GetMapping("/myProfile")
    public String sayHello(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("person", personDetails.getPerson());
        return "myProfile";
    }

//    @GetMapping("/showUserInfo")
//    public String showUserInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//        System.out.println(personDetails.getPerson());
//
//        return "myProfile";
//    }

    @GetMapping("/admin")
    public String adminPage(Model model) {

        List<Person> people = personDetailsService.loadAllUser();

        model.addAttribute("people", people);

        return "admin";
    }

    @GetMapping("/admin/ban/{id}")
    public String performRegistration(@PathVariable("id") int id) {

        if ("ROLE_USER".equals(personDetailsService.loadUserById(id).getRole())) {
            personDetailsService.deleteById(id);
        }

        return "redirect:/admin";
    }
}

//<form th:method="DELETE" th:action="@{/people/{id}(id=${person.getId()})}">
//<input type="submit" value="Delite"/>
