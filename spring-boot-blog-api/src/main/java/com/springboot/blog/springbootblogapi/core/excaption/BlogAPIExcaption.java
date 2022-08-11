package com.springboot.blog.springbootblogapi.core.excaption;

import org.springframework.http.HttpStatus;

public class BlogAPIExcaption extends RuntimeException  {
	private HttpStatus status;
	private String message;

	public BlogAPIExcaption(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public HttpStatus getHttpStatus()
	{
		return status;
	}
	public String getMessage()
	{
		return message;
	}

}
