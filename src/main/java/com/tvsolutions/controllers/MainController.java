package com.tvsolutions.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    String homePage(Principal principal, Model model){
        if(principal!=null) {
            model.addAttribute("principal", principal);
            return "home" ;
        }
        else return "home";
    }

    @GetMapping("/authenticated")
    String authPage(Principal principal, Model model){

        if(principal!=null) {
            model.addAttribute("principal", principal);
        }
        return "auth" ;
    }

    @GetMapping("/admin")
    String adminPage(Principal principal, Model model){

        if(principal!=null) {
            model.addAttribute("principal", principal);
        }
        return "admin" ;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('READ_PROFILE')")
    String profilePage(Principal principal, Model model){

        if(principal!=null) {
            model.addAttribute("principal", principal);
        }
        return "profile" ;
    }
}
