package com.atm.backend.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class RoleEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
}
