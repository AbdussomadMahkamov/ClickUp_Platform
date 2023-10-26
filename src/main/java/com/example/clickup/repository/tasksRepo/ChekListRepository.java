package com.example.clickup.repository.tasksRepo;

import com.example.clickup.entitiy.tasks.ChekList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChekListRepository extends JpaRepository<ChekList, UUID> {
    Optional<ChekList> findByNomiAndTasksIdAndUsersId(String nomi, UUID tasks_id, UUID users_id);
}
