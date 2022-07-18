package ru.Mak.nir.entities;

import lombok.*;
import ru.Mak.nir.DTO.RepeatedOperationDTO;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "repeated_operation")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class RepeatedOperation extends Base {
    @Column (name = "day")
    private Integer day;
    @Column (name = "description")
    private String description;
    @Column (name = "sum")
    private Float sum;
    @Column (name = "forward_planned")
    private Integer forwardPlanned;
    @CollectionTable (name = "operation_type", joinColumns = @JoinColumn (name = "ro_id"))
    @Enumerated (EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToMany
    private Set<Tag> tags;

    public RepeatedOperation(RepeatedOperationDTO ro, User user) {
        this.setId(ro.getId());
        this.day = ro.getDay();
        this.description = ro.getDescription();
        this.sum = ro.getSum();
        this.forwardPlanned = ro.getForwardPlanned();
        this.user = user;
        try {
            operationType = OperationType.valueOf(ro.getOperationType());
        } catch (IllegalArgumentException e) {
            operationType = OperationType.MONTLY;
        }
    }

    public RepeatedOperationDTO roToDTO() {
        return new RepeatedOperationDTO(this);
    }
}
