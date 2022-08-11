package com.springboot.blog.springbootblogapi.reporsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.blog.springbootblogapi.entities.concrete.Comment;


public interface CommentDao extends JpaRepository<Comment, Long> {
	List<Comment> findByPostId(long postId);

}
