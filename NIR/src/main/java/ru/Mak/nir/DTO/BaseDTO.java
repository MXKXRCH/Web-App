package ru.Mak.nir.DTO;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseDTO {
    private Long id;
}
