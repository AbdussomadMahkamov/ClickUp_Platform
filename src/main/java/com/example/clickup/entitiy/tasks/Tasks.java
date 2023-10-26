package com.example.clickup.entitiy.tasks;

import com.example.clickup.entitiy.abstractentity.UuidAbstractEntity;
import com.example.clickup.entitiy.folder.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tasks extends UuidAbstractEntity {
    private String nomi;
    private String izoh;
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp berilganVaqt;
    private Timestamp yakunlashVaqt;
    @ManyToOne
    private Priority priority;
    @ManyToOne
    private Dependencies dependencies;
    @ManyToOne
    private Lists lists;
}
