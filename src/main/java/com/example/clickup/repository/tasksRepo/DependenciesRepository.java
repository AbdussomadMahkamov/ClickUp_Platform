package com.example.clickup.repository.tasksRepo;

import com.example.clickup.entitiy.tasks.Dependencies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DependenciesRepository extends JpaRepository<Dependencies, Long> {
    Optional<Dependencies> findByNomi(String nomi);
}
