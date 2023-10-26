package com.example.clickup.entitiy.tasks;

import com.example.clickup.entitiy.Users;
import com.example.clickup.entitiy.abstractentity.UuidAbstractEntity;
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
public class ChekList extends UuidAbstractEntity {
    private String nomi;
    @ManyToOne
    private Tasks tasks;
    @OneToOne
    private Users users;
}
