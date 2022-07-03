package ru.Mak.nir.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.Mak.nir.entities.Operation;
import ru.Mak.nir.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/operations")
public class OperationController {
    @Autowired
    OperationService operationService;

    @RequestMapping(value = "{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> operationById(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<Operation>(HttpStatus.BAD_REQUEST);

        Operation operation = operationService.getById(id);

        if (operation == null)
            return new ResponseEntity<Operation>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Operation>(operation, HttpStatus.OK);
    }

    @RequestMapping(value = "",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> createOperation(@RequestBody Operation operation,
                                         @RequestParam Long userId) {
        if (userId == null)
            return new ResponseEntity<Operation>(HttpStatus.BAD_REQUEST);

        if (operation == null)
            return new ResponseEntity<Operation>(HttpStatus.BAD_REQUEST);

        operation = operationService.create(operation, userId);
        return new ResponseEntity<Operation>(operation, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> putOperation (@PathVariable Long id,
                                         @RequestBody Operation operation) {
        if (id == null || operation == null)
            return new ResponseEntity<Operation>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Operation>(operationService.update(id, operation), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> deleteOperation(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<Operation>(HttpStatus.BAD_REQUEST);

        Operation operation = operationService.getById(id);

        if (operation == null)
            return new ResponseEntity<Operation>(HttpStatus.NOT_FOUND);

        operationService.delete(id);
        return new ResponseEntity<Operation>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/all",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Operation>> getAllProducts() {
        List<Operation> operations = operationService.getAll();

        if (operations.isEmpty())
            return new ResponseEntity<List<Operation>>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Operation>>(operations, HttpStatus.OK);
    }
}
