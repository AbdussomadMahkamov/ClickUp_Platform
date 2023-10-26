package com.example.clickup.repository.workspaceRepo;

import com.example.clickup.entitiy.Users;
import com.example.clickup.entitiy.workspace.WorkSpaceUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkSpaceUsersRepository extends JpaRepository<WorkSpaceUsers, UUID> {
    Optional<WorkSpaceUsers> findByUsersIdAndWorkSpaceId(UUID users_id, Long workSpace_id);
    List<WorkSpaceUsers> findAllByWorkSpaceId(Long workSpace_id);
    Optional<WorkSpaceUsers> findByUsersId(UUID users_id);
}
