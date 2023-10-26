package com.example.clickup.service.tasksServicePackage;

import com.example.clickup.entitiy.tasks.Dependencies;
import com.example.clickup.entitiy.tasks.Priority;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.TasksDto;

public interface TasksService {
    ApiResponse addDependecies(Dependencies dependencies);

    ApiResponse addPriority(Priority priority);

    ApiResponse addTasks(Long priorityId, Long dependenciesId, Long listsId, Long folderId, TasksDto tasksDto);
}
