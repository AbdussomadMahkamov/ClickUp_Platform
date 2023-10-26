package com.example.clickup.entitiy.space;

import com.example.clickup.entitiy.abstractentity.LongAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SpaceView extends LongAbstractEntity {
    private String nomi;
}
