package ru.Mak.nir.services;

import ru.Mak.nir.entities.Goal;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.GoalRepo;
import ru.Mak.nir.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
    @Autowired
    private GoalRepo goalRepo;
    @Autowired
    private UserRepo userRepo;

    public Goal createGoal(Goal goal, Long userId) {
        User user = userRepo.findById(userId).get();
        //GoalPK id = new GoalPK(userId, );
        //goalEntity.setGoalPK(id);
        goal.setUser(user);

        return goalRepo.save(goal);
    }

    public Goal updateGoal(Long goalId, Goal goal) {
        goal.setId(goalId);
        return goalRepo.save(goal);
    }

    public Goal getGoalById(Long goalId) {
        return goalRepo.getById(goalId);
    }

    public Long deleteGoalById(Long goalId) {
        goalRepo.deleteById(goalId);
        return goalId;
    }
}
