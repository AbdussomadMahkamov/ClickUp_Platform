package com.example.clickup.repository.workspaceRepo;

import com.example.clickup.entitiy.workspace.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> {
    boolean existsByNomiAndUsersId(String nomi, UUID users_id);
}
