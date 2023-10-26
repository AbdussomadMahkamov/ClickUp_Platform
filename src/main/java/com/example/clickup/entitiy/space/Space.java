package com.example.clickup.entitiy.space;

import com.example.clickup.entitiy.workspace.WorkSpace;
import com.example.clickup.entitiy.abstractentity.LongAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Space extends LongAbstractEntity {
    private String nomi;
    private String rangi;
    private boolean korishTuri;
    @OneToOne
    private Template template;
    @ManyToOne
    private WorkSpace workSpace;
}
