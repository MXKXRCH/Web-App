package ru.Mak.nir.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
public class TagPK {
    private Long userId;
    private String tagName;
}
