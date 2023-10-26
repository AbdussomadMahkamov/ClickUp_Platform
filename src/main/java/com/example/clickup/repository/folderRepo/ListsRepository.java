package com.example.clickup.repository.folderRepo;

import com.example.clickup.entitiy.folder.Lists;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListsRepository extends JpaRepository<Lists, Long> {
    Optional<Lists> findByNomi(String nomi);
    Optional<Lists> findByNomiAndFolderId(String nomi, Long folder_id);
}
