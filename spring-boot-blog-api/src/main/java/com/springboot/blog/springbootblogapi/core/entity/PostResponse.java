package com.springboot.blog.springbootblogapi.core.entity;

import java.util.List;

import com.springboot.blog.springbootblogapi.entities.dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

	private List<PostDto> content;
	private int pageNo;
	private int pageSize;
	private long totalElemenets;
	private int totalPages;
	private boolean last;
}
