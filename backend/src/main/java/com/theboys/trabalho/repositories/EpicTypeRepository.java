package com.theboys.trabalho.repositories;

import com.theboys.trabalho.models.EpicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EpicTypeRepository extends JpaRepository<EpicType, UUID> {
}
