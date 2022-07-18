package ru.Mak.nir.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Mak.nir.DTO.UserDTO;
import ru.Mak.nir.exceptions.UserAlreadyExistsException;
import ru.Mak.nir.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO user,
                                              @RequestBody String pass) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            userService.save(user, pass);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> putUser (@PathVariable("id") Long id,
                                            @RequestBody @Valid UserDTO user) {
        if (id == null || user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user = userService.update(id, user);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        UserDTO user = userService.getById(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<UserDTO> users = userService.getAll(pageable);

        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
