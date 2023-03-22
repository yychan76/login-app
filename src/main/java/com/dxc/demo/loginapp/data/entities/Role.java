package com.dxc.demo.loginapp.data.entities;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private String name;

    private String authority;

}
