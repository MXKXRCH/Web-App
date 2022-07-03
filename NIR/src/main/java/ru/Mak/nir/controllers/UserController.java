package ru.Mak.nir.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> userById(@RequestBody Long id) {
        if (id == null)
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

        User user = userService.getById(id);

        if (user == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null)
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

        try {
            user = userService.addUser(user);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteGoal(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

        User user = userService.getById(id);

        if (user == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
