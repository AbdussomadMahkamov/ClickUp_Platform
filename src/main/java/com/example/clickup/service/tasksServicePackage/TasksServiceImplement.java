package com.example.clickup.service.tasksServicePackage;

import com.example.clickup.entitiy.folder.FolderUser;
import com.example.clickup.entitiy.folder.Lists;
import com.example.clickup.entitiy.tasks.*;
import com.example.clickup.entitiy.workspace.WorkSpaceUsers;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.ChekListGet;
import com.example.clickup.payload.TasksDto;
import com.example.clickup.repository.UsersRepository;
import com.example.clickup.repository.folderRepo.FolderUserRepository;
import com.example.clickup.repository.folderRepo.ListsRepository;
import com.example.clickup.repository.tasksRepo.*;
import com.example.clickup.repository.workspaceRepo.WorkSpaceUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TasksServiceImplement implements TasksService {
    @Autowired
    DependenciesRepository dependenciesRepository;
    @Autowired
    PriorityRepository priorityRepository;
    @Autowired
    TasksRepository tasksRepository;
    @Autowired
    TasksUsersRepository tasksUsersRepository;
    @Autowired
    SubTaskRepository subTaskRepository;
    @Autowired
    ListsRepository listsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    FolderUserRepository folderUserRepository;
    @Autowired
    ChekListRepository chekListRepository;
    @Autowired
    WorkSpaceUsersRepository workSpaceUsersRepository;

    @Override
    public ApiResponse addDependecies(Dependencies dependencies) {
        Optional<Dependencies> byNomi = dependenciesRepository.findByNomi(dependencies.getNomi());
        if (byNomi.isPresent()){
            return new ApiResponse("Bunday nom ostida dependecies saqlangan", false);
        }
        dependenciesRepository.save(dependencies);
        return new ApiResponse("Dependecies muvoffaqiyatli saqlandi", true);
    }

    @Override
    public ApiResponse addPriority(Priority priority) {
        Optional<Priority> byNomi = priorityRepository.findByNomi(priority.getNomi());
        if (byNomi.isPresent()){
            return new ApiResponse("Bunday nom ostida priority saqlangan", false);
        }
        priorityRepository.save(priority);
        return new ApiResponse("Priority muvoffaqiyatli saqlandi", true);
    }

    @Override
    public ApiResponse addTasks(Long priorityId, Long dependenciesId, Long listsId, Long folderId, TasksDto tasksDto) {
        Optional<Lists> lists = listsRepository.findById(listsId);
        if (!lists.isPresent()){
            return new ApiResponse("Bunday idli list mavjud emas!", false);
        }

        Optional<Tasks> byNomiAndListsId = tasksRepository.findByNomiAndListsId(tasksDto.getNomi(), listsId);
        if (byNomiAndListsId.isPresent()){
            return new ApiResponse("Bunday nom ostida listda task mavjud!", false);
        }
        Optional<Priority> priority = priorityRepository.findById(priorityId);
        if (!priority.isPresent()){
            return new ApiResponse("Bunday idli priority mavjud emas!", false);
        }
        Optional<Dependencies> dependencies = dependenciesRepository.findById(dependenciesId);
        if (!dependencies.isPresent()){
            return new ApiResponse("Bunday idli dependecies mavjud emas!", false);
        }
        Tasks tasks=new Tasks(
                tasksDto.getNomi(),
                tasksDto.getIzoh(),
                new Timestamp(System.currentTimeMillis()),
                tasksDto.getYakunlashVaqt(),
                priority.get(),
                dependencies.get(),
                lists.get()
        );
//        tasks.setNomi(tasksDto.getNomi());
//        tasks.setIzoh(tasksDto.getIzoh());
//        tasks.setBerilganVaqt(new Timestamp(System.currentTimeMillis()));
//        tasks.setYakunlashVaqt(tasksDto.getYakunlashVaqt());
//        tasks.setDependencies(dependencies.get());
//        tasks.setPriority(priority.get());
//        tasks.setLists(lists.get());
        tasksRepository.save(tasks);
        List<SubTasks> subTasksList=new ArrayList<>();
        for (String subTask : tasksDto.getSubTasks()) {
            Optional<SubTasks> byNomiAndTasksId = subTaskRepository.findByNomiAndTasksId(subTask, tasks.getId());
            if (!byNomiAndTasksId.isPresent()){
                subTasksList.add(new SubTasks(subTask, tasks));
            }
        }
        subTaskRepository.saveAll(subTasksList);
        List<TasksUsers> tasksUsersList=new ArrayList<>();
        for (UUID uuid : tasksDto.getUserList()) {
            Optional<FolderUser> folderUser = folderUserRepository.findByFolderIdAndUsersId(folderId, uuid);
            if (folderUser.isPresent()){
                tasksUsersList.add(new TasksUsers(tasks, folderUser.get().getUsers()));
            }
        }
        tasksUsersRepository.saveAll(tasksUsersList);
        List<ChekList> chekLists=new ArrayList<>();
        for (ChekListGet chekList : tasksDto.getChekLists()) {
            Optional<WorkSpaceUsers> byUsersId = workSpaceUsersRepository.findByUsersId(chekList.getUserId());
            if (byUsersId.isPresent()){
                Optional<ChekList> byNomiAndTasksIdAndUsersId = chekListRepository.findByNomiAndTasksIdAndUsersId(chekList.getNomi(), tasks.getId(), byUsersId.get().getUsers().getId());
                if (!byNomiAndTasksIdAndUsersId.isPresent()){
                    chekLists.add(new ChekList(chekList.getNomi(), tasks, byUsersId.get().getUsers()));
                }
            }
        }
        chekListRepository.saveAll(chekLists);
        return new ApiResponse("Yangi task yaratildi!", true);
    }
}
