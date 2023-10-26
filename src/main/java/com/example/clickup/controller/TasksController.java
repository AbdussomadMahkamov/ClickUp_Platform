package com.example.clickup.controller;

import com.example.clickup.entitiy.tasks.Dependencies;
import com.example.clickup.entitiy.tasks.Priority;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.TasksDto;
import com.example.clickup.service.tasksServicePackage.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    @Autowired
    TasksService tasksService;
    @PreAuthorize(value = "hasAuthority('MANAGE_TASK')")
    @PostMapping("/addDependecies")
    public HttpEntity<?> AddDependecies(@RequestBody Dependencies dependencies){
        ApiResponse apiResponse=tasksService.addDependecies(dependencies);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PreAuthorize(value = "hasAuthority('MANAGE_TASK')")
    @PostMapping("/addPriority")
    public HttpEntity<?> AddPriority(@RequestBody Priority priority){
        ApiResponse apiResponse=tasksService.addPriority(priority);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PreAuthorize(value = "hasAuthority('ManageTask')")
    @PostMapping("/addTasks/{priorityId}/{dependenciesId}/{listsId}/{folderId}")
    public HttpEntity<?> AddTasks(@PathVariable Long priorityId, @PathVariable Long dependenciesId, @PathVariable Long listsId, @PathVariable Long folderId, @RequestBody TasksDto tasksDto){
        ApiResponse apiResponse=tasksService.addTasks(priorityId, dependenciesId, listsId, folderId, tasksDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
}
