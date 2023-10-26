package com.example.clickup.repository.folderRepo;

import com.example.clickup.entitiy.folder.FolderUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FolderUserRepository extends JpaRepository<FolderUser, UUID> {
    Optional<FolderUser> findByFolderIdAndUsersId(Long folder_id, UUID users_id);
}
