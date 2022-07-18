package ru.Mak.nir.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.Mak.nir.DTO.UserDTO;
import ru.Mak.nir.entities.Role;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.exceptions.UserAlreadyExistsException;
import ru.Mak.nir.repos.UserRepo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void save(UserDTO userDTO, String pass) throws UserAlreadyExistsException {
        if (userRepo.findByUserName(userDTO.getName()) != null)
            throw new UserAlreadyExistsException("User with this name is already exists");
        User user = new User(userDTO, pass);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }

    public UserDTO update(Long userId, UserDTO userDTO) {
        User updatedUser = userRepo.findById(userId).orElse(null);
        if (updatedUser == null) {
            return null;
        }
        User user = new User(userDTO, updatedUser.getPass());
        user.setId(userId);
        return userRepo.save(user).userToDTO();
    }

    public UserDTO getById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        return (user == null) ? null : user.userToDTO();
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    public User findByUserName(String userName) {
        return this.userRepo.findByUserName(userName);
    }

    public List<UserDTO> getAll(Pageable pageable) {
        return userRepo.findAll(pageable).stream().map(User::userToDTO).collect(Collectors.toList());
    }
}
