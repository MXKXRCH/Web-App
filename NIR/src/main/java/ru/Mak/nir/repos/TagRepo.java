package ru.Mak.nir.repos;

import ru.Mak.nir.entities.Tag;
import ru.Mak.nir.entities.TagPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, TagPK> {
    Tag findByName(String name);
    List<Tag> findByIdUserId(Long userId);
    List<Tag> findByIdTagId(Long tagId);
}
