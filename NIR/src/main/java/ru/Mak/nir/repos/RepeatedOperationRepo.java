package ru.Mak.nir.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.Mak.nir.entities.RepeatedOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepeatedOperationRepo extends JpaRepository<RepeatedOperation, Long> {
    @Override
    Page<RepeatedOperation> findAll(Pageable pageable);
}
