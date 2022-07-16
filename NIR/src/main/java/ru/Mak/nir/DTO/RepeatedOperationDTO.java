package ru.Mak.nir.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.Mak.nir.entities.OperationType;
import ru.Mak.nir.entities.RepeatedOperation;
import ru.Mak.nir.entities.Tag;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class RepeatedOperationDTO extends BaseDTO {
    @NotNull
    @Min(value = 1, message = "Day must be greater or equals 1")
    @Max(value = 31, message = "Day must be less or equals 31")
    private Integer day;
    private String description;
    private Float sum;
    @NotNull
    @Min(value = 1, message = "Planned days value must be greater or equals 1")
    private Integer forwardPlanned;
    private String operationType;

    private List<String> tagNames;

    private static final int MAX_DAILY_COUNT = 1;
    private static final int MAX_WEEKLY_COUNT = 7;

    public RepeatedOperationDTO(RepeatedOperation ro) {
        this.setId(ro.getId());
        this.day = ro.getDay();
        this.description = ro.getDescription();
        this.sum = ro.getSum();
        this.forwardPlanned = ro.getForwardPlanned();
        this.operationType = ro.getOperationType().toString();
        if (this.operationType.equals(OperationType.DAILY.toString()) && day > MAX_DAILY_COUNT) {
            day = MAX_DAILY_COUNT;
        } else if (this.operationType.equals(OperationType.WEEKLY.toString()) && day > MAX_WEEKLY_COUNT) {
            day = MAX_WEEKLY_COUNT;
        }
        for (Tag tag : ro.getTags()) {
            tagNames.add(tag.getName());
        }
    }
}
