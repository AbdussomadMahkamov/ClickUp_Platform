package com.example.clickup.service.folderServicePackage;

import com.example.clickup.entitiy.folder.Lists;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.FolderDto;

public interface FolderService {
    ApiResponse addFolderLists(Lists lists);
    ApiResponse addFolder(FolderDto folderDto, Long spaceId, Long templateId);
}
