package com.example.clickup.entitiy.workspace;

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
public class ReserveUsers extends LongAbstractEntity {
    private String username;
    @ManyToOne
    private WorkSpace workSpace;
    @ManyToOne
    private WorkSpaceLavozimlari workSpaceLavozimlari;
}
