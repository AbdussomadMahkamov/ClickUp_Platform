package com.example.clickup.repository.folderRepo;

import com.example.clickup.entitiy.folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    Optional<Folder> findByNomiAndSpaceId(String nomi, Long space_id);
}
