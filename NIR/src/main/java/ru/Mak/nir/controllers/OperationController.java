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
import ru.Mak.nir.DTO.OperationDTO;
import ru.Mak.nir.entities.Operation;
import ru.Mak.nir.services.OperationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/operations", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationController {
    @Autowired
    OperationService operationService;

    @GetMapping("/{id}")
    public ResponseEntity<OperationDTO> getOperation(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        OperationDTO operation = operationService.getById(id);
        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OperationDTO> createOperation(@RequestBody @Valid OperationDTO operation,
                                                        @RequestParam Long userId) {
        if (userId == null || operation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        operationService.save(operation, userId);
        return new ResponseEntity<>(operation, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationDTO> putOperation (@PathVariable Long id,
                                                      @RequestBody @Valid OperationDTO operation) {
        if (id == null || operation == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(operationService.update(id, operation), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operation> deleteOperation(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        OperationDTO operation = operationService.getById(id);

        if (operation == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        operationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OperationDTO>> getAllOperations(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<OperationDTO> operations = operationService.getAll(pageable);

        if (operations.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
}
