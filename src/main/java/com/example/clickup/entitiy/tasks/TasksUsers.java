package com.example.clickup.entitiy.tasks;

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
public class TasksUsers extends UuidAbstractEntity {
    @ManyToOne
    private Tasks tasks;
    @ManyToOne
    private Users users;
}
