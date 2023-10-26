package com.example.clickup.repository.spaceRepo;

import com.example.clickup.entitiy.space.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpaceRepository extends JpaRepository<Space, Long> {
    Optional<Space> findByNomiAndWorkSpaceIdAndTemplateId(String nomi, Long workSpace_id, Long template_id);
}
