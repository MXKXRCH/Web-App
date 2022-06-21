package ru.Mak.nir.repos;

import ru.Mak.nir.entities.RepeatedOperation;
import ru.Mak.nir.entities.RepeatedOperationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepeatedOperationRepo extends JpaRepository<RepeatedOperation, RepeatedOperationPK> {
    List<RepeatedOperation> findByIdUserId(Long userId);
    List<RepeatedOperation> findByIdRepeatedOperationId(Long repeatedOperation);
}
