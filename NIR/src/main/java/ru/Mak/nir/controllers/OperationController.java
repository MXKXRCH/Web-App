package ru.Mak.nir.controllers;

import ru.Mak.nir.entities.Operation;
import ru.Mak.nir.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/operations",
                produces="application/json")
public class OperationController {
    @Autowired
    private OperationService operationService;

    @GetMapping("/{userId}/{opId}")
    public ResponseEntity operationById(@PathVariable("userId") Long userId,
                                        @PathVariable("opId") Long opId) {
        try {
            return ResponseEntity.ok(operationService.getOperationById(userId, opId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Operation not found");
        }
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity createOperation(@RequestBody Operation operation,
                                          @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(operationService.createOperation(operation, userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Operation error");
        }
    }

    @PutMapping(value = "/{userId}/{opId}", consumes="application/json")
    public Operation putOperation (@PathVariable("userId") Long userId,
                                   @PathVariable("opId") Long opId,
                                   @RequestBody Operation operation) {
        return operationService.updateOperation(userId, opId, operation);
    }


    @DeleteMapping("/{userId}/{opId}")
    public ResponseEntity deleteOperation(@PathVariable("userId") Long userId,
                                          @PathVariable("opId") Long opId) {
        try {
            operationService.deleteOperationById(userId, opId);
            return ResponseEntity.ok("Operation deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Operation error");
        }
    }
}
