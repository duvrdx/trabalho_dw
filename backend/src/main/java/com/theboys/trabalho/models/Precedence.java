package com.theboys.trabalho.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class Precedence {
    @Id
    @GeneratedValue
    private UUID id; // Utilizando UUID como identificador

    @ManyToOne
    private Task predecessor;

    @ManyToOne
    private Task successor;
}
