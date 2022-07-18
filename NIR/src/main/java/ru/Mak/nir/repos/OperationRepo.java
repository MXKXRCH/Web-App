package ru.Mak.nir.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.Mak.nir.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OperationRepo extends JpaRepository<Operation, Long> {
    List <Operation> findAllByOperationTime(Date...operationTime);
    @Override
    Page<Operation> findAll(Pageable pageable);
}
