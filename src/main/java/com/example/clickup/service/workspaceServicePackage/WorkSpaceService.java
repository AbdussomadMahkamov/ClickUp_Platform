package com.example.clickup.service.workspaceServicePackage;

import com.example.clickup.entitiy.Users;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.UsersDto;
import com.example.clickup.payload.WorkSpaceUsersDto;
import com.example.clickup.payload.WorkSpaceDto;

public interface WorkSpaceService {
    ApiResponse addWorkSpace(WorkSpaceDto workSpaceDto, Users users);

    ApiResponse addEditRemove(WorkSpaceUsersDto workSpaceUsersDto, Long workspaceId, Long lavozimId);

    ApiResponse joinUser(String username, Long workspaceid);

    ApiResponse signUpForJoin(UsersDto usersDto, Long workSpaceId);
}
