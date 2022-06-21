package ru.Mak.nir.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table (name = "Operation")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Operation {
    @EmbeddedId
    private OperationPK operationPK;

    @Column (name = "operationTime")
    private Date operationTime = new Date();
    @Column (name = "description")
    private String description;
    @Column (name = "plannedSum")
    private Float plannedSum;
    @Column (name = "realSum")
    private Float realSum;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @MapsId("tagName")
    @ManyToOne
    @JoinColumn (name = "tag_id")
    private Tag tag;
}
