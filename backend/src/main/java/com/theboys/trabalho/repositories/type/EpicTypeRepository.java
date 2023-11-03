package com.theboys.trabalho.repositories.type;

import com.theboys.trabalho.models.type.EpicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EpicTypeRepository extends JpaRepository<EpicType, UUID> {
}
