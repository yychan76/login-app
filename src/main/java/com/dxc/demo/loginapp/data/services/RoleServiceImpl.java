package com.dxc.demo.loginapp.data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dxc.demo.loginapp.data.entities.Role;
import com.dxc.demo.loginapp.data.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

}
