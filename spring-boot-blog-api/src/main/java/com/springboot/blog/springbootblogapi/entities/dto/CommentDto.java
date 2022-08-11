package com.springboot.blog.springbootblogapi.entities.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CommentDto {
	private long id;
	@NotEmpty(message = "Email should not be null or empty")
	@Email
	private String email;
	
	@NotEmpty(message = "Name	 should not be null or empty")
	private String name;
	@NotEmpty
	@Size(min=10,message = "Post body should have at least 10 characters ")
	private String body;

}
