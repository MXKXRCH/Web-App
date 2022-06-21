package ru.Mak.nir.controllers;

import ru.Mak.nir.entities.RepeatedOperation;
import ru.Mak.nir.services.RepeatedOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/repeatedOperations",
                produces="application/json")
public class RepeatedOperationController {
    @Autowired
    private RepeatedOperationService operationService;

    @GetMapping("/{opId}")
    public ResponseEntity operationById(@PathVariable("opId") Long opId) {
        try {
            return ResponseEntity.ok(operationService.getOperationById(opId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Operation not found");
        }
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity createRepeatedOperation(@RequestBody RepeatedOperation operation,
                                                  @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(operationService.createRepeatedOperation(operation, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Operation error");
        }
    }

    @PutMapping(value = "/{opId}", consumes="application/json")
    public RepeatedOperation putOperation (@PathVariable("opId") Long opId,
                                           @RequestBody RepeatedOperation operation) {
        return operationService.updateOperation(opId, operation);
    }

    @DeleteMapping("/{opId}")
    public ResponseEntity deleteOperation(@PathVariable("opId") Long opId) {
        try {
            operationService.deleteOperationById(opId);
            return ResponseEntity.ok("Operation deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Operation error");
        }
    }
}
