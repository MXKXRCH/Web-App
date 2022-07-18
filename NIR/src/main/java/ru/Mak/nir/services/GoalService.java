package ru.Mak.nir.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.Mak.nir.DTO.GoalDTO;
import ru.Mak.nir.entities.Goal;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.GoalRepo;
import ru.Mak.nir.repos.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {
    @Autowired
    private GoalRepo goalRepo;
    @Autowired
    private UserRepo userRepo;

    public void save(GoalDTO goalDTO, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        goalRepo.save(new Goal(goalDTO, user));
    }

    public GoalDTO update(Long goalId, GoalDTO goalDTO) {
        Goal updatedGoal = goalRepo.findById(goalId).orElse(null);
        if (updatedGoal == null) {
            return null;
        }
        Goal goal = new Goal(goalDTO, updatedGoal.getUser());
        goal.setTags(updatedGoal.getTags());
        goal.setId(goalId);
        return goalRepo.save(goal).toGoalDTO();
    }

    public GoalDTO getById(Long id) {
        Goal goal = goalRepo.findById(id).orElse(null);
        return (goal == null) ? null : goal.toGoalDTO();
    }

    public void delete(Long goalId) {
        goalRepo.deleteById(goalId);
    }

    public List<GoalDTO> getAll(Pageable pageable) {
        return goalRepo.findAll(pageable).stream().map(Goal::toGoalDTO).collect(Collectors.toList());
    }
}
