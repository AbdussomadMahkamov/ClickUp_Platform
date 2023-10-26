package com.example.clickup.service.folderServicePackage;

import com.example.clickup.entitiy.Users;
import com.example.clickup.entitiy.folder.Folder;
import com.example.clickup.entitiy.folder.FolderUser;
import com.example.clickup.entitiy.folder.Lists;
import com.example.clickup.entitiy.space.Space;
import com.example.clickup.entitiy.space.SpaceUser;
import com.example.clickup.entitiy.space.Template;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.FolderDto;
import com.example.clickup.repository.UsersRepository;
import com.example.clickup.repository.folderRepo.FolderRepository;
import com.example.clickup.repository.folderRepo.FolderUserRepository;
import com.example.clickup.repository.folderRepo.ListsRepository;
import com.example.clickup.repository.spaceRepo.SpaceRepository;
import com.example.clickup.repository.spaceRepo.SpaceUserRepository;
import com.example.clickup.repository.spaceRepo.TemplateRepository;
import com.example.clickup.repository.workspaceRepo.WorkSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FolderServiceImplement implements FolderService{
    @Autowired
    FolderRepository folderRepository;
    @Autowired
    ListsRepository listsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    WorkSpaceRepository workSpaceRepository;
    @Autowired
    SpaceRepository spaceRepository;
    @Autowired
    TemplateRepository templateRepository;
    @Autowired
    FolderUserRepository folderUserRepository;
    @Autowired
    SpaceUserRepository spaceUserRepository;

    @Override
    public ApiResponse addFolderLists(Lists lists) {
        Optional<Lists> byNomi = listsRepository.findByNomi(lists.getNomi());
        if (byNomi.isPresent()){
            return new ApiResponse("Bunday nom ostidagi List avvaldan mavjud!", false);
        }
        listsRepository.save(lists);
        return new ApiResponse("Yangi List saqlandi.", true);
    }

    @Override
    public ApiResponse addFolder(FolderDto folderDto, Long spaceId, Long templateId) {
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Space> space = spaceRepository.findById(spaceId);
        if (!space.isPresent()){
            return new ApiResponse("Bunday Space mavjud emas!", false);
        }
        Optional<Template> template = templateRepository.findById(templateId);
        if (!template.isPresent()){
            return new ApiResponse("Bunday Template mavjud emas!", false);
        }
        Optional<Folder> folderspace = folderRepository.findByNomiAndSpaceId(folderDto.getNomi(), spaceId);
        if (folderspace.isPresent()){
            return new ApiResponse("Kechirasiz bunday nom ostida folder spacega saqlangan!", false);
        }
        Folder folder=new Folder(
                folderDto.getNomi(),
                folderDto.isKorishTuri(),
                template.get(),
                space.get()
        );
        List<Lists> lists=new ArrayList<>();
        for (String s : folderDto.getListNomi()) {
            Optional<Lists> byNomiAndFolderId = listsRepository.findByNomiAndFolderId(s, folder.getId());
            if (byNomiAndFolderId.isPresent()){
                return new ApiResponse("Bunday nom ostida bu folderda saqlangan", false);
            }
            lists.add(new Lists(s, folder));
        }
        folderRepository.save(folder);
        listsRepository.saveAll(lists);
        List<FolderUser> folderUsers=new ArrayList<>();
        folderUsers.add(new FolderUser(folder, users));
        if (!folderDto.isKorishTuri()){
            for (UUID uuid : folderDto.getUserList()) {
                Optional<SpaceUser> bySpaceIdAndUsersId = spaceUserRepository.findBySpaceIdAndUsersId(spaceId, uuid);
                if (bySpaceIdAndUsersId.isPresent()){
                    folderUsers.add(new FolderUser(folder, bySpaceIdAndUsersId.get().getUsers()));
                }
            }
            folderUserRepository.saveAll(folderUsers);
            return new ApiResponse("Yangi folder private sifatida saqlandi!", true);
        }
        for (SpaceUser spaceUser : spaceUserRepository.findBySpaceId(spaceId)) {
            if (!spaceUser.equals(users)){
                folderUsers.add(new FolderUser(folder, spaceUser.getUsers()));
            }
        }
        folderUserRepository.saveAll(folderUsers);
        return new ApiResponse("Yangi folder public sifatida saqlandi!", true);
    }
}
