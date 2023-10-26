package com.example.clickup.entitiy.folder;

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
public class FolderUser extends UuidAbstractEntity {
    @ManyToOne
    private Folder folder;
    @ManyToOne
    private Users users;
}
