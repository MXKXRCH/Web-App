package ru.Mak.nir.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.Mak.nir.DTO.RepeatedOperationDTO;
import ru.Mak.nir.entities.RepeatedOperation;
import ru.Mak.nir.entities.User;
import ru.Mak.nir.repos.RepeatedOperationRepo;
import ru.Mak.nir.repos.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepeatedOperationService {
    @Autowired
    private RepeatedOperationRepo repeatedOperationRepo;
    @Autowired
    private UserRepo userRepo;

    public void save(RepeatedOperationDTO repeatedOperationDTO, Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        repeatedOperationRepo.save(new RepeatedOperation(repeatedOperationDTO, user));
    }

    public RepeatedOperationDTO update(Long repeatedOperationId, RepeatedOperationDTO repeatedOperationDTO) {
        RepeatedOperation updatedRepeatedOperation = repeatedOperationRepo.findById(repeatedOperationId).orElse(null);
        if (updatedRepeatedOperation == null) {
            return null;
        }
        RepeatedOperation repeatedOperation = new RepeatedOperation(repeatedOperationDTO, updatedRepeatedOperation.getUser());
        repeatedOperation.setTags(updatedRepeatedOperation.getTags());
        repeatedOperation.setId(repeatedOperationId);
        return repeatedOperationRepo.save(repeatedOperation).roToDTO();
    }

    public RepeatedOperationDTO getById(Long id) {
        RepeatedOperation repeatedOperation = repeatedOperationRepo.findById(id).orElse(null);
        return (repeatedOperation == null) ? null : repeatedOperation.roToDTO();
    }

    public void delete(Long repeatedOperationId) {
        repeatedOperationRepo.deleteById(repeatedOperationId);
    }

    public List<RepeatedOperationDTO> getAll(Pageable pageable) {
        return repeatedOperationRepo.findAll(pageable).stream().map(RepeatedOperation::roToDTO).collect(Collectors.toList());
    }
}
