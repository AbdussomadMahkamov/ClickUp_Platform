package com.example.clickup.service.spaceServicePackage;

import com.example.clickup.entitiy.Users;
import com.example.clickup.entitiy.space.*;
import com.example.clickup.entitiy.workspace.WorkSpace;
import com.example.clickup.entitiy.workspace.WorkSpaceUsers;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.SpaceDto;
import com.example.clickup.payload.StatusDto;
import com.example.clickup.payload.TemplateDto;
import com.example.clickup.repository.spaceRepo.*;
import com.example.clickup.repository.workspaceRepo.WorkSpaceRepository;
import com.example.clickup.repository.workspaceRepo.WorkSpaceUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpaceServiceImplement implements SpaceService{
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    TemplateRepository templateRepository;
    @Autowired
    TemplateStatusThirdTableRepository templateStatusThirdTableRepository;
    @Autowired
    SpaceAppRepository spaceAppRepository;
    @Autowired
    SpaceViewRepository spaceViewRepository;
    @Autowired
    SpaceAppSpaceRepository spaceAppSpaceRepository;
    @Autowired
    SpaceViewSpaceRepository spaceViewSpaceRepository;
    @Autowired
    WorkSpaceRepository workSpaceRepository;
    @Autowired
    SpaceRepository spaceRepository;
    @Autowired
    WorkSpaceUsersRepository workSpaceUsersRepository;
    @Autowired
    SpaceUserRepository spaceUserRepository;
    @Override
    public ApiResponse addStatus(StatusDto statusDto) {
        List<Status> statuses=new ArrayList<>();
        for (String s : statusDto.getStatuslist()) {
            Status status=new Status(s);
            statuses.add(status);
        }
        statusRepository.saveAll(statuses);
        return new ApiResponse("Statuslar muvoffaqiyatli saqlandi!", true);
    }

    @Override
    public ApiResponse addTemplate(TemplateDto templateDto) {
        Optional<Template> byNomi = templateRepository.findByNomi(templateDto.getNomi());
        if (!byNomi.isPresent()){
            Template template=new Template(templateDto.getNomi());
            templateRepository.save(template);
            List<TemplateStatusThirdTable> templateStatusThirdTableList=new ArrayList<>();
            for (String s : templateDto.getStatuslist()) {
                Optional<Status> byNomi1 = statusRepository.findByNomi(s);
                if (byNomi1.isPresent()){
                    TemplateStatusThirdTable templateStatusThirdTable=new TemplateStatusThirdTable(
                            template,
                            byNomi1.get()
                    );
                    templateStatusThirdTableList.add(templateStatusThirdTable);
                }
            }
            templateStatusThirdTableRepository.saveAll(templateStatusThirdTableList);
            return new ApiResponse("Template saqlandi", true);
        }
        return new ApiResponse("Bunday template avvaldan mavjud", false);
    }

    @Override
    public ApiResponse addSpaceApp(SpaceApp spaceApp) {
        if (!spaceApp.getNomi().equals("")){
            boolean b = spaceAppRepository.existsByNomi(spaceApp.getNomi());
            if (b) return new ApiResponse("Bunday nom ostida Space App avalladan mavjud!", false);
            spaceAppRepository.save(spaceApp);
            return new ApiResponse("Space App muvoffaqiyatli saqlandi", true);
        }
        return new ApiResponse("Kechirasiz ma'lumotlarni to'ldirib qaytadan urinib ko'ring!", false);
    }

    @Override
    public ApiResponse addSpaceView(SpaceView spaceView) {
        if (!spaceView.getNomi().equals("")){
            boolean b = spaceViewRepository.existsByNomi(spaceView.getNomi());
            if (b) return new ApiResponse("Bunday nom ostida oldindan Space View mavjud!", false);
            spaceViewRepository.save(spaceView);
            return new ApiResponse("Space View muvoffaqiyatli saqlandi", true);
        }
        return new ApiResponse("Kechirasiz ma'lumotlarni to'ldirib qaytadan urinib ko'ring!", false);
    }

    @Override
    public ApiResponse addSpace(SpaceDto spaceDto, Long workSpaceId, Long templateId) {
        Optional<WorkSpace> byId = workSpaceRepository.findById(workSpaceId);
        if (!byId.isPresent()){
            return new ApiResponse("Kechirasiz bunday idli WorkSpace topilamdi!", false);
        }
        Optional<Template> byId1 = templateRepository.findById(templateId);
        if (!byId1.isPresent()){
            return new ApiResponse("Kechirasiz bunday idli Template topilmadi!", false);
        }
        Optional<Space> byNomiAndWorkSpaceIdAndTemplateId = spaceRepository.findByNomiAndWorkSpaceIdAndTemplateId(spaceDto.getNomi(), workSpaceId, templateId);
        if (byNomiAndWorkSpaceIdAndTemplateId.isPresent()){
            return new ApiResponse("Space nomi, WorkSpace va Templatelar oldin birgalikda saqlangan! Tekshirib qayta ko'ring!", false);
        }
        Space space=new Space(
                spaceDto.getNomi(),
                spaceDto.getRangi(),
                spaceDto.isKorishTuri(),
                byId1.get(),
                byId.get()
        );
        Space save = spaceRepository.save(space);
        List<SpaceAppSpace> spaceAppSpaces=new ArrayList<>();
        for (Long aLong : spaceDto.getSpaceAppId()) {
            Optional<SpaceApp> byId2 = spaceAppRepository.findById(aLong);
            if (byId2.isPresent()){
                SpaceAppSpace appSpace=new SpaceAppSpace(save, byId2.get());
                spaceAppSpaces.add(appSpace);
            }
        }
        spaceAppSpaceRepository.saveAll(spaceAppSpaces);
        List<SpaceViewSpace> spaceViews=new ArrayList<>();
        for (Long aLong : spaceDto.getSpaceViewId()) {
            Optional<SpaceView> byId2 = spaceViewRepository.findById(aLong);
            if (byId2.isPresent()){
                SpaceViewSpace viewSpace=new SpaceViewSpace(space, byId2.get());
                spaceViews.add(viewSpace);
            }
        }
        spaceViewSpaceRepository.saveAll(spaceViews);
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SpaceUser> spaceUserList=new ArrayList<>();
        SpaceUser spaceUsern=new SpaceUser(space, users);
        spaceUserList.add(spaceUsern);
        if (spaceDto.isKorishTuri()){
            List<WorkSpaceUsers> allByWorkSpaceId = workSpaceUsersRepository.findAllByWorkSpaceId(workSpaceId);
            for (WorkSpaceUsers workSpaceUsers : allByWorkSpaceId) {
                SpaceUser spaceUser=new SpaceUser(space, workSpaceUsers.getUsers());
                spaceUserList.add(spaceUser);
            }
            spaceUserRepository.saveAll(spaceUserList);
        }
        else {
            for (UUID uuid : spaceDto.getUserList()) {
                Optional<WorkSpaceUsers> byUsersIdAndWorkSpaceId = workSpaceUsersRepository.findByUsersIdAndWorkSpaceId(uuid, workSpaceId);
                if (byUsersIdAndWorkSpaceId.isPresent()){
                    SpaceUser spaceUser=new SpaceUser(space, byUsersIdAndWorkSpaceId.get().getUsers());
                    spaceUserList.add(spaceUser);
                }
            }
            spaceUserRepository.saveAll(spaceUserList);
        }
        return new ApiResponse("Space muvoffaqiyatli saqlandi", true);
    }
}
