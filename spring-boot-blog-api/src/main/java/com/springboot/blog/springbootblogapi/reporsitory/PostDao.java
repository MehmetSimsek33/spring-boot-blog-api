package com.springboot.blog.springbootblogapi.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.springbootblogapi.entities.concrete.Post;

@Repository	
public interface PostDao extends JpaRepository<Post, Long>{

}
