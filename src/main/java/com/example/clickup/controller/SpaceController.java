package com.example.clickup.controller;

import com.example.clickup.entitiy.space.SpaceApp;
import com.example.clickup.entitiy.space.SpaceView;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.SpaceDto;
import com.example.clickup.payload.StatusDto;
import com.example.clickup.payload.TemplateDto;
import com.example.clickup.service.spaceServicePackage.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/space")
public class SpaceController {
    @Autowired
    SpaceService spaceService;
    @PostMapping("/addStatus")
    public HttpEntity<?> AddStatus(@RequestBody StatusDto statusDto){
        ApiResponse apiResponse=spaceService.addStatus(statusDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PreAuthorize(value = "hasAuthority('CREATE_SPACE')")
    @PostMapping("/addTemplate")
    public HttpEntity<?> AddTemplate(@RequestBody TemplateDto templateDto){
        ApiResponse apiResponse=spaceService.addTemplate(templateDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PreAuthorize(value = "hasAuthority('CREATE_SPACE')")
    @PostMapping("addSpaceApp")
    public HttpEntity<?> AddSpaceApp(@RequestBody SpaceApp spaceApp){
        ApiResponse apiResponse=spaceService.addSpaceApp(spaceApp);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PostMapping("addSpaceView")
    public HttpEntity<?> AddSpaceView(@RequestBody SpaceView spaceView){
        ApiResponse apiResponse=spaceService.addSpaceView(spaceView);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
    @PreAuthorize(value = "hasAuthority('CreateSpace')")
    @PostMapping("addSpace/{workSpaceId}/{templateId}")
    public HttpEntity<?> AddSpace(@RequestBody SpaceDto spaceDto, @PathVariable Long workSpaceId, @PathVariable Long templateId){
        ApiResponse apiResponse=spaceService.addSpace(spaceDto, workSpaceId, templateId);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
}
