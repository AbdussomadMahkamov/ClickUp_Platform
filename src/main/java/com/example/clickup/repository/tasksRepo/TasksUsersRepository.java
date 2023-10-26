package com.example.clickup.repository.tasksRepo;

import com.example.clickup.entitiy.tasks.TasksUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TasksUsersRepository extends JpaRepository<TasksUsers, UUID> {
}
