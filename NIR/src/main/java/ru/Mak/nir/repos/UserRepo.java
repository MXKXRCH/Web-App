package ru.Mak.nir.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.Mak.nir.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    @Override
    Page<User> findAll(Pageable pageable);
}
