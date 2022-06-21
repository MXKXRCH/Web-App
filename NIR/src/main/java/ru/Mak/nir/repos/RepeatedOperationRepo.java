package ru.Mak.nir.repos;

import ru.Mak.nir.entities.RepeatedOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepeatedOperationRepo extends JpaRepository<RepeatedOperation, Long> {
}
