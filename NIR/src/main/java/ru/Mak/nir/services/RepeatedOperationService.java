package ru.Mak.nir.services;

import ru.Mak.nir.entities.RepeatedOperation;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.RepeatedOperationRepo;
import ru.Mak.nir.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepeatedOperationService {
    @Autowired
    private RepeatedOperationRepo operationRepo;
    @Autowired
    private UserRepo userRepo;

    public RepeatedOperation create(RepeatedOperation operation, Long userId) {
        User user = userRepo.findById(userId).get();
        if (user == null) return null;
        operation.setUser(user);
        return operationRepo.save(operation);
    }

    public RepeatedOperation update(Long opId, RepeatedOperation operation) {
        operation.setId(opId);
        return operationRepo.save(operation);
    }

    public RepeatedOperation getById(Long opId) {
        return operationRepo.getById(opId);
    }

    public Long delete(Long opId) {
        operationRepo.deleteById(opId);
        return opId;
    }

    public List<RepeatedOperation> getAll() {
        return operationRepo.findAll();
    }
}
