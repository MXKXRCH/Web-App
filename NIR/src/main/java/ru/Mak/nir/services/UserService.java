package ru.Mak.nir.services;

import ru.Mak.nir.entities.Role;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.UserRepo;
import ru.Mak.nir.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User addUser(User user) throws UserAlreadyExistsException {
        if (userRepo.findByUserName(user.getUserName()) != null)
            throw new UserAlreadyExistsException("User with this name is already exists");

        user.setRoles(Collections.singleton(Role.USER));
        return  userRepo.save(user);
    }

    public User getById(Long id) {
        return userRepo.getById(id);
    }

    public Iterable<User> allUsers() {
        return userRepo.findAll();
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);
        return id;
    }

    public User findByUserName(String userName) {
        return this.userRepo.findByUserName(userName);
    }
}
