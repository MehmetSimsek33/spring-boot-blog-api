package com.springboot.blog.springbootblogapi.reporsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.springbootblogapi.core.entity.concrete.User;

public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findByEmail (String email);
	Optional<User> findByUsername (String username);
	Optional<User> findByUsernameOrEmail (String username,String email);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);

	
}
