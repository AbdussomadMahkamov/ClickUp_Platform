package com.example.clickup.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TasksDto {
    private String nomi;
    private String izoh;
    private Timestamp yakunlashVaqt;
    private List<UUID> userList;
    private List<String> subTasks;
    private List<ChekListGet> chekLists;
    /*
    "nomi": "Tasks1",
    "izoh": "Hamma topshiriqni o'z vaqtida bajarsin",
    "yakunlashVaqt": "2023-05-25",
    "userList": ["829f7508-b2e2-434e-af37-80a83a9e42c5"],
    "subTasks": ["subTask1"],
    "chekLists": [
    {"nomi": "Tekshirish1",
    "userId":"829f7508-b2e2-434e-af37-80a83a9e42c5"}
    ]
     */
}
