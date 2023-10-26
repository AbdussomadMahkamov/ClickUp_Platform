package com.example.clickup.service.spaceServicePackage;

import com.example.clickup.entitiy.space.SpaceApp;
import com.example.clickup.entitiy.space.SpaceView;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.SpaceDto;
import com.example.clickup.payload.StatusDto;
import com.example.clickup.payload.TemplateDto;

public interface SpaceService {
    ApiResponse addStatus(StatusDto statusDto);

    ApiResponse addTemplate(TemplateDto templateDto);

    ApiResponse addSpaceApp(SpaceApp spaceApp);

    ApiResponse addSpaceView(SpaceView spaceView);

    ApiResponse addSpace(SpaceDto spaceDto, Long workSpaceId, Long templateId);
}
