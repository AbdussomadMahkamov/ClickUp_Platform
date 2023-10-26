package com.example.clickup.controller;

import com.example.clickup.entitiy.folder.Lists;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.FolderDto;
import com.example.clickup.service.folderServicePackage.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folder")
public class FolderController {
    @Autowired
    FolderService folderService;
    @PostMapping("/addFolderLists")
    public HttpEntity<?> addFolderLists(@RequestBody Lists lists){
        ApiResponse apiResponse=folderService.addFolderLists(lists);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());

    }
    @PostMapping("/addFolder/{spaceId}/{templateId}")
    public HttpEntity<?> AddFolder(@RequestBody FolderDto folderDto, @PathVariable Long spaceId, @PathVariable Long templateId){
        ApiResponse apiResponse=folderService.addFolder(folderDto, spaceId, templateId);
        return ResponseEntity.status(apiResponse.isHolat()?200:209).body(apiResponse.getXabar());
    }
}
