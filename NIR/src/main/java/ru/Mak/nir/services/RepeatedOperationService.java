package ru.Mak.nir.services;

import ru.Mak.nir.entities.RepeatedOperation;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.RepeatedOperationRepo;
import ru.Mak.nir.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepeatedOperationService {
    @Autowired
    private RepeatedOperationRepo operationRepo;
    @Autowired
    private UserRepo userRepo;

    public RepeatedOperation createRepeatedOperation(RepeatedOperation operation, Long userId) {
        User user = userRepo.findById(userId).get();
        operation.setUser(user);
        return operationRepo.save(operation);
    }

    public RepeatedOperation updateOperation(Long opId, RepeatedOperation operation) {
        operation.setId(opId);
        return operationRepo.save(operation);
    }

    public RepeatedOperation getOperationById(Long opId) {
        return operationRepo.getById(opId);
    }

    public Long deleteOperationById(Long opId) {
        operationRepo.deleteById(opId);
        return opId;
    }
}
