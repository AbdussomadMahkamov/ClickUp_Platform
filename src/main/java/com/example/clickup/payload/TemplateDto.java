package com.example.clickup.payload;

import lombok.Data;

import java.util.List;

@Data
public class TemplateDto {
    private String nomi;
    private List<String> statuslist;
}
