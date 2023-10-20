package com.theboys.trabalho.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
public class TaskType {
    @Id
    @GeneratedValue
    private UUID id; // Utilizando UUID como identificador

    private String name;
}