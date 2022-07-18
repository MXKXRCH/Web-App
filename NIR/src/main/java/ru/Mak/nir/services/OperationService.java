package ru.Mak.nir.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.Mak.nir.DTO.OperationDTO;
import ru.Mak.nir.entities.Operation;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.OperationRepo;
import ru.Mak.nir.repos.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private OperationRepo operationRepo;
    @Autowired
    private UserRepo userRepo;

    public void save(OperationDTO operation, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        operationRepo.save(new Operation(operation, user));
    }

    public OperationDTO update(Long opId, OperationDTO operationDTO) {
        Operation updatedOperation = operationRepo.findById(opId).orElse(null);
        if (updatedOperation == null) {
            return null;
        }
        Operation operation = new Operation(operationDTO, updatedOperation.getUser());
        operation.setId(opId);
        operation.setTags(updatedOperation.getTags());
        return operationRepo.save(operation).toOperationDTO();
    }

    public List<Operation> getOperationsByDate(Date minOperationTime, Date maxOperationTime) {
        return em.createQuery("SELECT op FROM Operation op WHERE operationTime > :paramMin and operationTime < :paramMax", Operation.class)
                .setParameter("paramMin", minOperationTime).setParameter("paramMax", maxOperationTime).getResultList();
    }

    public OperationDTO getById(Long id) {
        Operation operation = operationRepo.findById(id).orElse(null);
        return (operation == null) ? null : operation.toOperationDTO();
    }

    public void delete(Long opId) {
        operationRepo.deleteById(opId);
    }

    public List<OperationDTO> getAll(Pageable pageable) {
        return operationRepo.findAll(pageable).stream().map(Operation::toOperationDTO).collect(Collectors.toList());
    }
}
