package com.example.clickup.repository.workspaceRepo;

import com.example.clickup.entitiy.workspace.IshchilarSoni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IshchilarSoniRepository extends JpaRepository<IshchilarSoni, Long> {
    Optional<IshchilarSoni> findByIshchilarSoni(String ishchilarSoni);
}
