package com.springboot.blog.springbootblogapi.bussines.abstracts;

import java.util.List;

import com.springboot.blog.springbootblogapi.entities.concrete.Comment;
import com.springboot.blog.springbootblogapi.entities.dto.CommentDto;

public interface CommentService {
	CommentDto createCommnet(long postId, CommentDto commentDto);

	List<CommentDto> getCommentByPostId(long postId);

	CommentDto getCommentById(long postId, long commentId);
	
	CommentDto updateComment (Long postId,long commentId,CommentDto commentRequest);
	void deleteComment (Long postId,long commentId);
}
