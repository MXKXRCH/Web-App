package ru.Mak.nir.entities;

import lombok.*;
import ru.Mak.nir.DTO.GoalDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "goal")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Goal extends Base {
    @Column (name = "sum")
    private Float sum;
    @Column (name = "deadline")
    private Date deadLine;
    @Column (name = "created_date")
    private Date createdDate;
    @Column (name = "completed")
    private Boolean completed;
    @Column (name = "description")
    private String description;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToMany
    private Set<Tag> tags;

    public Goal(GoalDTO goalDTO, User user) {
        this.setId(goalDTO.getId());
        this.sum = goalDTO.getSum();
        this.deadLine = goalDTO.getDeadLine();
        this.createdDate = goalDTO.getCreatedDate();
        this.completed = goalDTO.getCompleted();
        this.description = goalDTO.getDescription();
        this.user = user;
    }

    public GoalDTO toGoalDTO() {
        return new GoalDTO(this);
    }
}
