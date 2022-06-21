package ru.Mak.nir.controllers;

import ru.Mak.nir.entities.User;
import ru.Mak.nir.exceptions.UserAlreadyExistsException;
import ru.Mak.nir.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        try {
            userService.addUser(user);
        } catch (UserAlreadyExistsException e) {
            model.put("message", e.getMessage());
            return "registration";
        }
        return "redirect:/login";
    }
}
