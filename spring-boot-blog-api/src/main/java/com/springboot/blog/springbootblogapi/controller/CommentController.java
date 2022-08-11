package com.springboot.blog.springbootblogapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.springbootblogapi.bussines.abstracts.CommentService;
import com.springboot.blog.springbootblogapi.entities.dto.CommentDto;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
			@Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createCommnet(postId, commentDto), HttpStatus.CREATED);

	}

	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentByPostId(@PathVariable(value = "postId") long postId) {
		return commentService.getCommentByPostId(postId);
	}

	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> getCommentByPostId(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "id") long commentId) {
		CommentDto commentDto = commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentDto, HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "id") long id, @Valid @RequestBody CommentDto commentDto) {
		CommentDto updateComment = commentService.updateComment(postId, id, commentDto);
		return new ResponseEntity<>(updateComment,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<String> deleteCommenet(@PathVariable(value = "postId") long postId,
												 @PathVariable(value = "id") long commentId)
	{
			commentService.deleteComment(postId, commentId);
			return new ResponseEntity<>("Comment deletede succesfully",HttpStatus.OK);
	}
}
