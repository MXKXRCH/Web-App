package ru.Mak.nir.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.Mak.nir.DTO.TagDTO;
import ru.Mak.nir.entities.Tag;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.TagRepo;
import ru.Mak.nir.repos.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private UserRepo userRepo;

    public void save(TagDTO tagDTO, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        tagRepo.save(new Tag(tagDTO, user));
    }

    public TagDTO update(Long tagId, TagDTO tagDTO) {
        Tag updatedTag = tagRepo.findById(tagId).orElse(null);
        if (updatedTag == null) {
            return null;
        }
        Tag tag = new Tag(tagDTO, updatedTag.getUser());
        tag.setId(tagId);
        return tagRepo.save(tag).tagToDTO();
    }

    public TagDTO getById(Long id) {
        Tag tag = tagRepo.findById(id).orElse(null);
        return (tag == null) ? null : tag.tagToDTO();
    }

    public void delete(Long tagId) {
        tagRepo.deleteById(tagId);
    }

    public List<TagDTO> getAll(Pageable pageable) {
        return tagRepo.findAll(pageable).stream().map(Tag::tagToDTO).collect(Collectors.toList());
    }
}
