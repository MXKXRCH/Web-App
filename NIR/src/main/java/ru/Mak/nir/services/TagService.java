package ru.Mak.nir.services;

import ru.Mak.nir.repos.TagRepo;
import ru.Mak.nir.entities.Tag;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.exceptions.TagAlreadyExistsException;
import ru.Mak.nir.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private UserRepo userRepo;

    public Tag create(Tag tag, Long userId) throws TagAlreadyExistsException {
        if (tagRepo.findByName(tag.getName()) != null)
            throw new TagAlreadyExistsException("Tag with this name is already exists");
        User user = userRepo.findById(userId).get();
        tag.setUser(user);
        if (user == null) return null;
        return tagRepo.save(tag);
    }

    public Tag update(Long tagId, Tag tag) {
        tag.setId(tagId);
        return tagRepo.save(tag);
    }

    public Tag getById(Long tagId) {
        return tagRepo.getById(tagId);
    }

    public Long delete(Long tagId) {
        tagRepo.deleteById(tagId);
        return tagId;
    }

    public List<Tag> getAll() {
        return tagRepo.findAll();
    }
}
