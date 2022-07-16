package ru.Mak.nir.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.Mak.nir.entities.Operation;
import ru.Mak.nir.entities.Tag;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class OperationDTO extends BaseDTO {
    @NotNull
    @NotBlank
    @Past(message = "Operation date must be less or equals than today's date")
    private Date operationTime;
    @NotNull
    @Size(min=5, max=255, message = "Minimum symbols: 3\nMaximum symbols: 255")
    private String description;
    @NotNull
    @Min(value = 1, message = "Planned sum must be greater or equals 1")
    private Float plannedSum;
    @NotNull
    @Min(value = 1, message = "Real sum must be greater or equals 1")
    private Float realSum;

    private List<String> tagNames;

    public OperationDTO(Operation operation) {
        this.setId(operation.getId());
        this.operationTime = operation.getOperationTime();
        this.description = operation.getDescription();
        this.plannedSum = operation.getPlannedSum();
        this.realSum = operation.getRealSum();
        for (Tag tag : operation.getTags()) {
            tagNames.add(tag.getName());
        }
    }
}
