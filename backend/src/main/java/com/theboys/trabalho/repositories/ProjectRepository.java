package com.theboys.trabalho.repositories;

import com.theboys.trabalho.models.Epic;
import com.theboys.trabalho.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
