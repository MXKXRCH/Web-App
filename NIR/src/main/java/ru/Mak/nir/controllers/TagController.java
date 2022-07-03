package ru.Mak.nir.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.Mak.nir.entities.Tag;
import ru.Mak.nir.exceptions.TagAlreadyExistsException;
import ru.Mak.nir.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/tags")
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping(value = "{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> tagById(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<Tag>(HttpStatus.BAD_REQUEST);

        Tag tag = tagService.getById(id);

        if (tag == null)
            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Tag>(tag, HttpStatus.OK);
    }

    @RequestMapping(value = "",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag,
                                         @RequestParam Long userId) {
        if (userId == null || tag == null)
            return new ResponseEntity<Tag>(HttpStatus.BAD_REQUEST);

        try {
            tag = tagService.create(tag, userId);
        } catch (TagAlreadyExistsException e) {
            return new ResponseEntity<Tag>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Tag>(tag, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> putTag (@PathVariable Long id,
                                         @RequestBody Tag tag) {
        if (id == null || tag == null)
            return new ResponseEntity<Tag>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Tag>(tagService.update(id, tag), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> deleteTag(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<Tag>(HttpStatus.BAD_REQUEST);

        Tag tag = tagService.getById(id);

        if (tag == null)
            return new ResponseEntity<Tag>(HttpStatus.NOT_FOUND);

        tagService.delete(id);
        return new ResponseEntity<Tag>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/all",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> getAllProducts() {
        List<Tag> tags = tagService.getAll();

        if (tags.isEmpty())
            return new ResponseEntity<List<Tag>>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
    }
}
