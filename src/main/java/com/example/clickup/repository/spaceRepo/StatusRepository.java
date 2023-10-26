package com.example.clickup.repository.spaceRepo;

import com.example.clickup.entitiy.space.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByNomi(String nomi);
}
