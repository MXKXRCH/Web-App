package ru.Mak.nir.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.Mak.nir.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepo extends JpaRepository<Goal, Long> {
    @Override
    Page<Goal> findAll(Pageable pageable);
}
