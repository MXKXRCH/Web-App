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
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "tag_id")
    private Tag tag;
}
