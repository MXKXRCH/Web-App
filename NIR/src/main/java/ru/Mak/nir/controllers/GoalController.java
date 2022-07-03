package ru.Mak.nir.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.Mak.nir.entities.Goal;
import ru.Mak.nir.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/goals")
public class GoalController {
    @Autowired
    GoalService goalService;

    @RequestMapping(value = "{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goal> goalById(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<Goal>(HttpStatus.BAD_REQUEST);

        Goal goal = goalService.getById(id);

        if (goal == null)
            return new ResponseEntity<Goal>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Goal>(goal, HttpStatus.OK);
    }

    @RequestMapping(value = "",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goal> createGoal(@RequestBody Goal goal,
                                         @RequestParam Long userId) {
        if (userId == null)
            return new ResponseEntity<Goal>(HttpStatus.BAD_REQUEST);

        if (goal == null)
            return new ResponseEntity<Goal>(HttpStatus.BAD_REQUEST);

        goal = goalService.create(goal, userId);
        return new ResponseEntity<Goal>(goal, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goal> putGoal (@PathVariable Long id,
                                         @RequestBody Goal goal) {
        if (id == null || goal == null)
            return new ResponseEntity<Goal>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Goal>(goalService.update(id, goal), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goal> deleteGoal(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<Goal>(HttpStatus.BAD_REQUEST);

        Goal goal = goalService.getById(id);

        if (goal == null)
            return new ResponseEntity<Goal>(HttpStatus.NOT_FOUND);

        goalService.delete(id);
        return new ResponseEntity<Goal>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/all",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Goal>> getAllProducts() {
        List<Goal> goals = goalService.getAll();

        if (goals.isEmpty())
            return new ResponseEntity<List<Goal>>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Goal>>(goals, HttpStatus.OK);
    }
}
