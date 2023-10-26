package com.example.clickup.repository.workspaceRepo;

import com.example.clickup.entitiy.workspace.ReserveUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReserveUsersRepository extends JpaRepository<ReserveUsers, Long> {
    Optional<ReserveUsers> findByUsernameAndWorkSpaceId(String username, Long workSpace_id);

    Optional<ReserveUsers> findByUsername(String username);
}
