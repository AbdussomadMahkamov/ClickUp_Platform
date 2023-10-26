package com.example.clickup.payload;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SpaceDto {
    private String nomi;
    private String rangi;
    private boolean korishTuri;
    private List<Long> spaceAppId;
    private List<Long> spaceViewId;
    private List<UUID> userList;
    /*
    "nomi": "Kundalik",
    "rangi": "Green",
    "korishTuri": true,
    "spaceAppId": [],
    "spaceViewId": [],
    "userList": []
     */
}
