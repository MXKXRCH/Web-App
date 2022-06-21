package ru.Mak.nir.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table (name = "Goal")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Goal {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "sum")
    private Float sum;
    @Column (name = "deadLine")
    private Date deadLine = new Date();
    @Column (name = "startDate")
    private Date startDate;
    @Column (name = "completed")
    private Boolean completed;
    @Column (name = "description")
    private String description;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "tag_id")
    private Tag tag;
}
