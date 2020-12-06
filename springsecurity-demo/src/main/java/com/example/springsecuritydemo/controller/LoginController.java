package com.example.springsecuritydemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

//    @PreAuthorize("hasRole('admin')") //具有adin权限
//    @Secured("ROLE_abC") // 具有abC角色
//    @PreAuthorize("hasRole('abC')") // 具有abC角色
    // Secured和PreAuthorize二选一即可， 但Secured中角色必须有ROLE_前缀
    @RequestMapping(value = "/toMain", method = RequestMethod.POST)
    public String toMain() {
        return "redirect:/main.html";
    }

    @RequestMapping(value = "/toError", method = RequestMethod.POST)
    public String toError() {
        return "redirect:/error.html";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }
}
