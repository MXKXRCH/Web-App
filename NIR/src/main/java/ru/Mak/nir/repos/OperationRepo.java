package ru.Mak.nir.repos;

import ru.Mak.nir.entities.Operation;
import ru.Mak.nir.entities.OperationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OperationRepo extends JpaRepository<Operation, OperationPK> {
    List <Operation> findAllByOperationTime(Date...operationTime);
    List <Operation> findByIdUserId(Long userId);
    List <Operation> findByIdOperationId(Long operationId);
}
