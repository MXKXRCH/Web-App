package ru.Mak.nir.repos;

import ru.Mak.nir.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
