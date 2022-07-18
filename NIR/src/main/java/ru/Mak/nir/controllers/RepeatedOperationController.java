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
import ru.Mak.nir.DTO.RepeatedOperationDTO;
import ru.Mak.nir.services.RepeatedOperationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/repeatedOperations", produces = MediaType.APPLICATION_JSON_VALUE)
public class RepeatedOperationController {
    @Autowired
    RepeatedOperationService repeatedOperationService;

    @GetMapping("/{id}")
    public ResponseEntity<RepeatedOperationDTO> getRepeatedOperation(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RepeatedOperationDTO repeatedOperation = repeatedOperationService.getById(id);
        if (repeatedOperation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(repeatedOperation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RepeatedOperationDTO> createRepeatedOperation(@RequestBody @Valid RepeatedOperationDTO repeatedOperation,
                                              @RequestParam Long userId) {
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (repeatedOperation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repeatedOperationService.save(repeatedOperation, userId);
        return new ResponseEntity<>(repeatedOperation, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepeatedOperationDTO> putRepeatedOperation (@PathVariable("id") Long id,
                                            @RequestBody @Valid RepeatedOperationDTO repeatedOperation) {
        if (id == null || repeatedOperation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repeatedOperation = repeatedOperationService.update(id, repeatedOperation);
        if (repeatedOperation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(repeatedOperation, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RepeatedOperationDTO> deleteRepeatedOperation(@PathVariable("id") Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        RepeatedOperationDTO repeatedOperation = repeatedOperationService.getById(id);

        if (repeatedOperation == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        repeatedOperationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RepeatedOperationDTO>> getAllRepeatedOperations(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<RepeatedOperationDTO> repeatedOperations = repeatedOperationService.getAll(pageable);

        if (repeatedOperations.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(repeatedOperations, HttpStatus.OK);
    }
}
