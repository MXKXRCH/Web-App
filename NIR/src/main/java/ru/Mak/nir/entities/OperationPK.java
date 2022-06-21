package ru.Mak.nir.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
public class OperationPK implements Serializable {
    private Long userId;
    private Long operationId;
}
