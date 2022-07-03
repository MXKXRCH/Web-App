package ru.Mak.nir.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.Mak.nir.entities.RepeatedOperation;
import ru.Mak.nir.services.RepeatedOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/repeatedOperations")
public class RepeatedOperationController {
    @Autowired
    RepeatedOperationService repeatedOperationService;

    @RequestMapping(value = "{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepeatedOperation> repeatedOperationById(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<RepeatedOperation>(HttpStatus.BAD_REQUEST);

        RepeatedOperation repeatedOperation = repeatedOperationService.getById(id);

        if (repeatedOperation == null)
            return new ResponseEntity<RepeatedOperation>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<RepeatedOperation>(repeatedOperation, HttpStatus.OK);
    }

    @RequestMapping(value = "",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepeatedOperation> createRepeatedOperation(@RequestBody RepeatedOperation repeatedOperation,
                                         @RequestParam Long userId) {
        if (userId == null)
            return new ResponseEntity<RepeatedOperation>(HttpStatus.BAD_REQUEST);

        if (repeatedOperation == null)
            return new ResponseEntity<RepeatedOperation>(HttpStatus.BAD_REQUEST);

        repeatedOperation = repeatedOperationService.create(repeatedOperation, userId);
        return new ResponseEntity<RepeatedOperation>(repeatedOperation, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepeatedOperation> putRepeatedOperation (@PathVariable Long id,
                                         @RequestBody RepeatedOperation repeatedOperation) {
        if (id == null || repeatedOperation == null)
            return new ResponseEntity<RepeatedOperation>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<RepeatedOperation>(repeatedOperationService.update(id, repeatedOperation), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepeatedOperation> deleteRepeatedOperation(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<RepeatedOperation>(HttpStatus.BAD_REQUEST);

        RepeatedOperation repeatedOperation = repeatedOperationService.getById(id);

        if (repeatedOperation == null)
            return new ResponseEntity<RepeatedOperation>(HttpStatus.NOT_FOUND);

        repeatedOperationService.delete(id);
        return new ResponseEntity<RepeatedOperation>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/all",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RepeatedOperation>> getAllProducts() {
        List<RepeatedOperation> repeatedOperations = repeatedOperationService.getAll();

        if (repeatedOperations.isEmpty())
            return new ResponseEntity<List<RepeatedOperation>>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<RepeatedOperation>>(repeatedOperations, HttpStatus.OK);
    }
}
