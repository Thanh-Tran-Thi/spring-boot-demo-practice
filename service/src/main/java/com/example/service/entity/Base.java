package com.example.service.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(EntityListeners.class)
public abstract class Base {

    @Column
    @CreationTimestamp
    private Date createAt;

    @Column
    @UpdateTimestamp
    private Date updateAt;
}
