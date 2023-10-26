package com.example.clickup.repository.tasksRepo;

import com.example.clickup.entitiy.tasks.SubTasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubTaskRepository extends JpaRepository<SubTasks, UUID> {
    Optional<SubTasks> findByNomiAndTasksId(String nomi, UUID tasks_id);
}
