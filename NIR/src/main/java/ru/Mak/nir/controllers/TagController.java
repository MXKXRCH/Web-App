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
import ru.Mak.nir.DTO.TagDTO;
import ru.Mak.nir.services.TagService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/tags", produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTag(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TagDTO tag = tagService.getById(id);
        if (tag == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody @Valid TagDTO tag,
                                              @RequestParam Long userId) {
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (tag == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        tagService.save(tag, userId);
        return new ResponseEntity<>(tag, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> putTag (@PathVariable("id") Long id,
                                            @RequestBody @Valid TagDTO tag) {
        if (id == null || tag == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        tag = tagService.update(id, tag);
        if (tag == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tag, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TagDTO> deleteTag(@PathVariable("id") Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        TagDTO tag = tagService.getById(id);

        if (tag == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        tagService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TagDTO>> getAllTags(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<TagDTO> tags = tagService.getAll(pageable);

        if (tags.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
