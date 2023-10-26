package com.example.clickup.repository.tasksRepo;

import com.example.clickup.entitiy.tasks.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TasksRepository extends JpaRepository<Tasks, UUID> {
    Optional<Tasks> findByNomiAndListsId(String nomi, Long lists_id);
}
