package com.example.clickup.entitiy.tasks;

import com.example.clickup.entitiy.abstractentity.LongAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Dependencies extends LongAbstractEntity {
    private String nomi;
    private String izoh;
}
