package com.example.clickup.entitiy.space;

import com.example.clickup.entitiy.Users;
import com.example.clickup.entitiy.abstractentity.UuidAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SpaceUser extends UuidAbstractEntity {
    @ManyToOne
    private Space space;
    @ManyToOne
    private Users users;
}
