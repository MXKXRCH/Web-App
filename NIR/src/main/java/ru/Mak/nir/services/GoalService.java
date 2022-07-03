package ru.Mak.nir.services;

import ru.Mak.nir.entities.Goal;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.GoalRepo;
import ru.Mak.nir.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {
    @Autowired
    private GoalRepo goalRepo;
    @Autowired
    private UserRepo userRepo;

    public Goal create(Goal goal, Long userId) {
        User user = userRepo.findById(userId).get();
        if (user == null) return null;
        goal.setUser(user);
        return goalRepo.save(goal);
    }

    public Goal update(Long goalId, Goal goal) {
        goal.setId(goalId);
        return goalRepo.save(goal);
    }

    public Goal save(Goal goal) {
        return goalRepo.save(goal);
    }

    public Goal getById(Long goalId) {
        return goalRepo.getById(goalId);
    }

    public void delete(Long goalId) {
        goalRepo.deleteById(goalId);
    }

    public List<Goal> getAll() {
        return goalRepo.findAll();
    }
}
