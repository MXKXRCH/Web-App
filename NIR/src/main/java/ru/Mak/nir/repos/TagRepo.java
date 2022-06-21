package ru.Mak.nir.repos;

import ru.Mak.nir.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
