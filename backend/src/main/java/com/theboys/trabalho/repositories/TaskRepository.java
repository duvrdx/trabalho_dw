package com.theboys.trabalho.repositories;

import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
