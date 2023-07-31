package com.resa.restfulapi.entity;

import jakarta.persistence.*;

/**
 * Created by Resa S.
 * Date: 31-07-2023
 * Created in IntelliJ IDEA.
 */
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
