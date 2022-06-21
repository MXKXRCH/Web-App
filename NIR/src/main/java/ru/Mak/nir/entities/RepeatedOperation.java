package ru.Mak.nir.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table (name = "Repeated_operation")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class RepeatedOperation {
    @EmbeddedId
    private RepeatedOperationPK repeatedOperationPK;

    @Column (name = "day")
    private Integer day;
    @Column (name = "description")
    private String description;
    @Column (name = "sum")
    private Float sum;
    @Column (name = "forwardPlanned")
    private Integer forwardPlanned;
    @CollectionTable (name = "operation_role", joinColumns = @JoinColumn (name = "ro_id"))
    @Enumerated (EnumType.STRING)
    private OperationType operationType;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @MapsId("tagName")
    @ManyToOne
    @JoinColumn (name = "tag_id")
    private Tag tag;
}
