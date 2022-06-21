package ru.Mak.nir.controllers;

import ru.Mak.nir.entities.User;
import ru.Mak.nir.exceptions.UserAlreadyExistsException;
import ru.Mak.nir.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/users",
                produces="application/json")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity userById(@RequestBody Long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity registration(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.ok("Registration completed");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration error");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("User deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration error");
        }
    }
}
