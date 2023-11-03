package com.theboys.trabalho.models.type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.UUID;

@Entity
@Data
@Accessors(chain = true)
@Table(name="task_type")
public class TaskType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;

    @ManyToOne
    @JoinColumn(name="user_story_type_id")
    @JsonManagedReference
    private UserStoryType userStoryType;
}
