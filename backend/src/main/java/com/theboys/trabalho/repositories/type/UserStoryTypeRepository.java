package com.theboys.trabalho.repositories.type;


import com.theboys.trabalho.models.type.UserStoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserStoryTypeRepository extends JpaRepository<UserStoryType, UUID> {
}
