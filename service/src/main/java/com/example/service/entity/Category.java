package com.example.service.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends Base{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int status;

}
