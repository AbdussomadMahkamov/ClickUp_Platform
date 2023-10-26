package com.example.clickup.repository.spaceRepo;

import com.example.clickup.entitiy.space.SpaceView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceViewRepository extends JpaRepository<SpaceView, Long> {
    boolean existsByNomi(String nomi);
}
