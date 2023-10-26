package com.example.clickup.repository.workspaceRepo;

import com.example.clickup.entitiy.workspace.Reklama;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReklamaRepository extends JpaRepository<Reklama, Long> {
    Optional<Reklama> findByNomi(String nomi);
}
