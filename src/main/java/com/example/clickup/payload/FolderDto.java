package com.example.clickup.payload;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FolderDto {
    private String nomi;
    private boolean korishTuri;
    private List<String> listNomi;
    private List<UUID> userList;
    /*
    "nomi": "folder1",
    "korishTuri": true,
    "listNomi": ["folderlist1"],
    "userList":[]
     */
}
