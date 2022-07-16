package ru.Mak.nir.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.Mak.nir.entities.Goal;
import ru.Mak.nir.entities.Tag;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GoalDTO extends BaseDTO {
    @NotNull
    @Min(value = 1, message = "Sum must be greater or equals 1")
    private Float sum;
    @NotNull
    @NotBlank
    @Past(message = "Completed date must be less or equals than today's date")
    private Date deadLine;
    @NotNull
    @NotBlank
    @Past(message = "Created date must be less or equals than today's date")
    private Date createdDate;
    @NotBlank
    private Boolean completed;
    @NotNull
    @Size(min=5, max=255, message = "Minimum symbols: 3\nMaximum symbols: 255")
    private String description;

    private List<String> tagNames;

    public GoalDTO(Goal goal) {
        this.setId(goal.getId());
        this.sum = goal.getSum();
        this.deadLine = goal.getDeadLine();
        this.createdDate = goal.getCreatedDate();
        this.completed = goal.getCompleted();
        this.description = goal.getDescription();
        for (Tag tag : goal.getTags()) {
            tagNames.add(tag.getName());
        }
    }
}
