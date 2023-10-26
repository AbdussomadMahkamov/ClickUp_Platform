package com.example.clickup.entitiy.space;

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
public class TemplateStatusThirdTable extends UuidAbstractEntity {
    @ManyToOne
    private Template template;
    @ManyToOne
    private Status status;
}
