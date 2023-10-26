package com.example.clickup.repository.spaceRepo;

import com.example.clickup.entitiy.space.SpaceApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceAppRepository extends JpaRepository<SpaceApp, Long> {
    boolean existsByNomi(String nomi);
}
