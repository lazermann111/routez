package com.routez.routez.entity;


import com.fasterxml.jackson.annotation.JsonView;
import com.routez.routez.View;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    @JsonView(View.Created.class)
    private Date created;

    @LastModifiedDate
    @JsonView(View.Created.class)
    private Date updated;
}
