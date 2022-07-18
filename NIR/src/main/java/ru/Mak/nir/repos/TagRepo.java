package ru.Mak.nir.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.Mak.nir.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
    @Override
    Page<Tag> findAll(Pageable pageable);
}
