package com.example.clickup.entitiy.folder;

import com.example.clickup.entitiy.abstractentity.LongAbstractEntity;
import com.example.clickup.entitiy.space.Space;
import com.example.clickup.entitiy.space.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Folder extends LongAbstractEntity {
    private String nomi;
    private boolean korishTuri;
    @ManyToOne
    private Template template;
    @ManyToOne
    private Space space;
}
