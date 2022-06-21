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

    @GetMapping("/{userId}/{goalId}")
    public ResponseEntity goalById(@PathVariable("userId") Long userId,
                                   @PathVariable("goalId") Long goalId) {
        try {
            return ResponseEntity.ok(goalService.getGoalById(userId, goalId));
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

    @PutMapping(value = "/{userId}/{goalId}", consumes="application/json")
    public Goal putGoal (@PathVariable("userId") Long userId,
                         @PathVariable("goalId") Long goalId,
                         @RequestBody Goal goal) {
        return goalService.updateGoal(userId, goalId, goal);
    }

    @DeleteMapping("/{userId}/{goalId}")
    public ResponseEntity deleteGoal(@PathVariable("userId") Long userId,
                                     @PathVariable("goalId") Long goalId) {
        try {
            goalService.deleteGoalById(userId, goalId);
            return ResponseEntity.ok("Goal deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Goal error");
        }
    }
}
