package com.springboot.blog.springbootblogapi.core.entity.dto;

import lombok.Data;

@Data
public class LoginDto {
	private String usernameOrEmail;
	private String password;

}
