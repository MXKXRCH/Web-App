package ru.Mak.nir.services;

import ru.Mak.nir.entities.Operation;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.OperationRepo;
import ru.Mak.nir.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class OperationService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private OperationRepo operationRepo;
    @Autowired
    private UserRepo userRepo;

    public Operation createOperation(Operation operation, Long userId) {
        User user = userRepo.findById(userId).get();
        operation.setUser(user);
        return operationRepo.save(operation);
    }

    public Operation updateOperation(Long opId, Operation operation) {
        operation.setId(opId);
        return operationRepo.save(operation);
    }

    public List<Operation> getOperationsByDate(Date minOperationTime, Date maxOperationTime) {
        return em.createQuery("SELECT op FROM Operation op WHERE operationTime > :paramMin and operationTime < :paramMax", Operation.class)
                .setParameter("paramMin", minOperationTime).setParameter("paramMax", maxOperationTime).getResultList();
    }

    public Operation getOperationById(Long opId) {
        return operationRepo.getById(opId);
    }

    public Long deleteOperationById(Long opId) {
        operationRepo.deleteById(opId);
        return opId;
    }
}
