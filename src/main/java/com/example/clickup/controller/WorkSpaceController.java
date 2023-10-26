package com.example.clickup.controller;

import com.example.clickup.entitiy.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.UsersDto;
import com.example.clickup.payload.WorkSpaceUsersDto;
import com.example.clickup.payload.WorkSpaceDto;
import com.example.clickup.security.CurrentUser;
import com.example.clickup.service.workspaceServicePackage.WorkSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workspace")
public class WorkSpaceController {
    @Autowired
    WorkSpaceService workSpaceService;
    @PreAuthorize(value = "hasAuthority('SYSTEMUSER')")
    @PostMapping("/addWorkSpace")
    public HttpEntity<?> AddWorkSpace(@RequestBody WorkSpaceDto workSpaceDto, @CurrentUser Users users){
        System.out.println(users.getFish());
        ApiResponse apiResponse=workSpaceService.addWorkSpace(workSpaceDto, users);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PostMapping("/AddEditRemove/{workspaceId}/{lavozimId}")
    public HttpEntity<?> AddEditRemove(@RequestBody WorkSpaceUsersDto workSpaceUsersDto, @PathVariable Long workspaceId, @PathVariable Long lavozimId){
        ApiResponse apiResponse=workSpaceService.addEditRemove(workSpaceUsersDto, workspaceId, lavozimId);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @GetMapping("/joinWorkSpace")
    public  HttpEntity<?> JoinWorkspace(@RequestParam String username, @RequestParam Long workspaceid){
        ApiResponse apiResponse=workSpaceService.joinUser(username, workspaceid);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PostMapping("/signUpForJoin")
    public HttpEntity<?> SignUpForJoin(@RequestBody UsersDto usersDto, @RequestParam Long workSpaceId){
        ApiResponse apiResponse = workSpaceService.signUpForJoin(usersDto, workSpaceId);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
}
