package ru.Mak.nir.entities;

import lombok.*;
import ru.Mak.nir.DTO.OperationDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "operation")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Operation extends Base {
    @Column (name = "operation_time")
    private Date operationTime;
    @Column (name = "description")
    private String description;
    @Column (name = "planned_sum")
    private Float plannedSum;
    @Column (name = "real_sum")
    private Float realSum;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToMany
    private Set<Tag> tags;

    public Operation(OperationDTO operationDTO, Set<Tag> tags, User user) {
        this.setId(operationDTO.getId());
        this.operationTime = operationDTO.getOperationTime();
        this.description = operationDTO.getDescription();
        this.plannedSum = operationDTO.getPlannedSum();
        this.realSum = operationDTO.getRealSum();
        this.tags = tags;
        this.user = user;
    }

    public OperationDTO toOperationDTO() {
        return new OperationDTO(this);
    }
}
