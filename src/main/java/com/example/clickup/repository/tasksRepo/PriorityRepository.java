package com.example.clickup.repository.tasksRepo;

import com.example.clickup.entitiy.tasks.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
    Optional<Priority> findByNomi(String nomi);
}
