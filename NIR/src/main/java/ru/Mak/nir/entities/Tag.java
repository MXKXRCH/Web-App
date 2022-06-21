package ru.Mak.nir.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table (name = "Tag")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Tag {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @MapsId("userId")
    @ManyToOne
    private User user;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "tag")
    private Set<Operation> operationEntities = new HashSet<>();
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "tag")
    private Set<Goal> goalEntities = new HashSet<>();
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "tag")
    private Set<RepeatedOperation> repeatedOperationEntities  = new HashSet<>();
}
