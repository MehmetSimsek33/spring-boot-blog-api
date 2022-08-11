package com.springboot.blog.springbootblogapi.bussines.abstracts;

import java.util.List;

import com.springboot.blog.springbootblogapi.core.entity.PostResponse;
import com.springboot.blog.springbootblogapi.entities.dto.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto);

	PostResponse getAllPosts(int pageNo ,int pageSize, String sortBy,String sortDir);

	PostDto getPostById(long id);

	PostDto updatePost(PostDto postDto,long id);
	
	void deletePostById(long id);
	
}
