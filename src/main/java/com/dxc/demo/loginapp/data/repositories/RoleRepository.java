package com.dxc.demo.loginapp.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.demo.loginapp.data.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
