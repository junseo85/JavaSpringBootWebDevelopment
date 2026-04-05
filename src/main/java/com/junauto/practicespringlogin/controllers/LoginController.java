package com.junauto.practicespringlogin.controllers;

import com.junauto.practicespringlogin.models.LoginModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/")
    public String displayLoginForm(Model model){
        model.addAttribute("loginModel", new LoginModel());
        return "loginForm.html";
    }

    @PostMapping("/processLogin")
    public String processLogin(@Valid @ModelAttribute("loginModel")LoginModel loginModel, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("loginModel", loginModel);
            return "loginForm";
        }
        model.addAttribute("loginModel", loginModel );
        return "loginResults";
    }
}
