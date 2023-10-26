package com.example.clickup.entitiy.space;

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
public class SpaceAppSpace extends LongAbstractEntity {
    @ManyToOne
    private Space space;
    @ManyToOne
    private SpaceApp spaceApp;
}
