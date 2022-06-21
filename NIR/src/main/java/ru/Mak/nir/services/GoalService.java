package ru.Mak.nir.services;

import ru.Mak.nir.entities.Goal;
import ru.Mak.nir.entities.GoalPK;
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

    public Goal updateGoal(Long userId, Long goalId, Goal goal) {
        GoalPK id = new GoalPK(userId, goalId);
        goal.setGoalPK(id);
        return goalRepo.save(goal);
    }

    public Goal getGoalById(Long userId, Long goalId) {
        GoalPK id = new GoalPK(userId, goalId);
        return goalRepo.getById(id);
    }

    public GoalPK deleteGoalById(Long userId, Long goalId) {
        GoalPK id = new GoalPK(userId, goalId);
        goalRepo.deleteById(id);
        return id;
    }
}
