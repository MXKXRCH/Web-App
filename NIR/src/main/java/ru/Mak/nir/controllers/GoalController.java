package ru.Mak.nir.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Mak.nir.DTO.GoalDTO;
import ru.Mak.nir.services.GoalService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/goals", produces = MediaType.APPLICATION_JSON_VALUE)
public class GoalController {
    @Autowired
    GoalService goalService;

    @GetMapping("/{id}")
    public ResponseEntity<GoalDTO> getGoal(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GoalDTO goal = goalService.getById(id);
        if (goal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(goal, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GoalDTO> createGoal(@RequestBody @Valid GoalDTO goal,
                                              @RequestParam Long userId) {
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (goal == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        goalService.save(goal, userId);
        return new ResponseEntity<>(goal, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoalDTO> putGoal (@PathVariable("id") Long id,
                                            @RequestBody @Valid GoalDTO goal) {
        if (id == null || goal == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        goal = goalService.update(id, goal);
        if (goal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(goal, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GoalDTO> deleteGoal(@PathVariable("id") Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        GoalDTO goal = goalService.getById(id);

        if (goal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        goalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GoalDTO>> getAllGoals(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<GoalDTO> goals = goalService.getAll(pageable);

        if (goals.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(goals, HttpStatus.OK);
    }
}
