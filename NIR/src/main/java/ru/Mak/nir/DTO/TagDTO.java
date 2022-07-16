package ru.Mak.nir.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.Mak.nir.entities.Goal;
import ru.Mak.nir.entities.Operation;
import ru.Mak.nir.entities.RepeatedOperation;
import ru.Mak.nir.entities.Tag;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TagDTO extends BaseDTO {
    @NotNull
    @Size(min=3, max=64, message = "Minimum symbols: 3\nMaximum symbols: 64")
    private String name;

    private HashMap<Long, String> operations;
    private List<String> repeatedOperations;
    private List<String> goals;

    public TagDTO(Tag tag) {
        this.setId(tag.getId());
        this.name = tag.getName();
        for(Operation op : tag.getOperations()) {
            operations.put(op.getId(), op.getOperationTime().toString() + "\n" + op.getDescription());
        }
        for(RepeatedOperation op : tag.getRepeatedOperations()) {
            operations.put(op.getId(), op.getDescription());
        }
        for(Goal goal : tag.getGoals()) {
            operations.put(goal.getId(), goal.getDeadLine() + "\n" + goal.getDescription());
        }
    }
}
