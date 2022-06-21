package ru.Mak.nir.controllers;

import ru.Mak.nir.entities.Tag;
import ru.Mak.nir.exceptions.TagAlreadyExistsException;
import ru.Mak.nir.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/tags",
                produces="application/json")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/{userId}/{tagId}")
    public ResponseEntity tagById(@PathVariable("userId") Long userId,
                                  @PathVariable("tagId") Long tagId) {
        try {
            return ResponseEntity.ok(tagService.getTagById(userId, tagId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tag not found");
        }
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity createTag(@RequestBody Tag tag,
                                    @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(tagService.createTag(tag, userId));
        } catch (TagAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tag error");
        }
    }

    @PutMapping(value = "/{userId}/{goalId}", consumes="application/json")
    public Tag putTag (@PathVariable("userId") Long userId,
                       @PathVariable("tagId") Long tagId,
                       @RequestBody Tag tag) {
        return tagService.updateTag(userId, tagId, tag);
    }

    @DeleteMapping("/{userId}/{tagId}")
    public ResponseEntity deleteTag(@PathVariable("userId") Long userId,
                                    @PathVariable("tagId") Long tagId) {
        try {
            tagService.delete(userId, tagId);
            return ResponseEntity.ok("Tag deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tag error");
        }
    }
}
