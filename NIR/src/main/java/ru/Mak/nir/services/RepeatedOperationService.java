package ru.Mak.nir.services;

import com.Mak.NIR.entities.*;
import ru.Mak.nir.entities.RepeatedOperation;
import ru.Mak.nir.entities.RepeatedOperationPK;
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

    public RepeatedOperation updateOperation(Long userId, Long opId, RepeatedOperation operation) {
        operation.setRepeatedOperationPK(new RepeatedOperationPK(userId, opId));
        return operationRepo.save(operation);
    }

    public RepeatedOperation getOperationById(Long userId, Long opId) {
        return operationRepo.getById(new RepeatedOperationPK(userId, opId));
    }

    public RepeatedOperationPK deleteOperationById(Long userId, Long opId) {
        RepeatedOperationPK id = new RepeatedOperationPK(userId, opId);
        operationRepo.deleteById(id);
        return id;
    }
}
