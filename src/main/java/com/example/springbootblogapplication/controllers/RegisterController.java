package com.example.springbootblogapplication.controllers;

import com.example.springbootblogapplication.models.Account;
import com.example.springbootblogapplication.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final AccountService accountService;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {

        Account account = new Account();
        model.addAttribute("account", account);
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account account, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        try {
            // Try to save the account, it will throw an exception if email is already in use
            accountService.save(account);
        } catch (IllegalArgumentException ex) {
            // If email is already in use, add an error message to the model
            redirectAttributes.addFlashAttribute("emailError", "Email is already in use.");
            return "redirect:/register"; // Redirect back to register page
        }

        // Redirect to home page if registration is successful
        return "redirect:/";
    }

}
