package ru.Mak.nir.services;

import com.Mak.NIR.entities.*;
import ru.Mak.nir.repos.TagRepo;
import ru.Mak.nir.entities.Tag;
import ru.Mak.nir.entities.TagPK;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.exceptions.TagAlreadyExistsException;
import ru.Mak.nir.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private UserRepo userRepo;

    public Tag createTag(Tag tag, Long userId) throws TagAlreadyExistsException {
        if (userRepo.findByUserName(tag.getName()) != null)
            throw new TagAlreadyExistsException("Tag with this name is already exists");
        User user = userRepo.findById(userId).get();
        tag.setUser(user);
        return tagRepo.save(tag);
    }

    public Tag updateTag(Long userId, Long tagId, Tag tag) {
        tag.setTagPK(new TagPK(userId, tagId));
        return tagRepo.save(tag);
    }

    public Tag getTagById(Long userId, Long tagId) {
        return tagRepo.getById(new TagPK(userId, tagId));
    }

    public TagPK delete(Long userId, Long tagId) {
        TagPK id = new TagPK(userId, tagId);
        tagRepo.deleteById(id);
        return id;
    }
}
