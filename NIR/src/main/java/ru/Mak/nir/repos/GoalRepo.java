package ru.Mak.nir.repos;

import ru.Mak.nir.entities.Goal;
import ru.Mak.nir.entities.GoalPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepo extends JpaRepository<Goal, GoalPK> {
    List<Goal> findByIdUserId(Long userId);
    List<Goal> findByIdGoalId(Long goalId);
}
