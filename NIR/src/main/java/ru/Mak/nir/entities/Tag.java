package ru.Mak.nir.entities;

import lombok.*;
import ru.Mak.nir.DTO.TagDTO;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "tag")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Tag extends Base {
    @Column (name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "tag_operation",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Operation> operations;
    @ManyToMany
    @JoinTable(
            name = "tag_goal",
            joinColumns = @JoinColumn(name = "goal_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Goal> goals;
    @ManyToMany
    @JoinTable(
            name = "tag_ro",
            joinColumns = @JoinColumn(name = "ro_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<RepeatedOperation> repeatedOperations;

    public Tag(TagDTO tagDTO, User user) {
        this.setId(tagDTO.getId());
        this.name = tagDTO.getName();
        this.user = user;
    }

    public TagDTO tagToDTO() {
        return new TagDTO(this);
    }
}
