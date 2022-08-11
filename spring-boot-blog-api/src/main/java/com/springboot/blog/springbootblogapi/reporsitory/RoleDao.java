package com.springboot.blog.springbootblogapi.reporsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.springbootblogapi.core.entity.concrete.Role;


public interface RoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
