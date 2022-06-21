package ru.Mak.nir.controllers;

import ru.Mak.nir.entities.Goal;
import ru.Mak.nir.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/goals",
                produces="application/json")
public class GoalController {
    @Autowired
    GoalService goalService;

    @GetMapping("/{goalId}")
    public ResponseEntity goalById(@PathVariable("goalId") Long goalId) {
        try {
            return ResponseEntity.ok(goalService.getGoalById(goalId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Operation not found");
        }
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity createGoal(@RequestBody Goal goal,
                                     @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(goalService.createGoal(goal, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Goal error");
        }
    }

    @PutMapping(value = "/{goalId}", consumes="application/json")
    public Goal putGoal (@PathVariable("goalId") Long goalId,
                         @RequestBody Goal goal) {
        return goalService.updateGoal(goalId, goal);
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity deleteGoal(@PathVariable("goalId") Long goalId) {
        try {
            goalService.deleteGoalById(goalId);
            return ResponseEntity.ok("Goal deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Goal error");
        }
    }
}
