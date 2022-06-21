package ru.Mak.nir.repos;

import ru.Mak.nir.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepo extends JpaRepository<Goal, Long> {
}
