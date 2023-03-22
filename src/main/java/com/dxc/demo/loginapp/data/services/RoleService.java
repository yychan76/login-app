package com.dxc.demo.loginapp.data.services;

import java.util.List;
import java.util.Optional;

import com.dxc.demo.loginapp.data.entities.Role;

public interface RoleService {

    List<Role> findAllRoles();

    Optional<Role> findRoleByName(String roleName);

}
