package com.example.clickup.entitiy.folder;

import com.example.clickup.entitiy.abstractentity.LongAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Lists extends LongAbstractEntity {
    private String nomi;
    @ManyToOne
    private Folder folder;
}
