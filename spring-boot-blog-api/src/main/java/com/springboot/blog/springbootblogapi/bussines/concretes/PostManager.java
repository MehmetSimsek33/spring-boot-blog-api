package com.springboot.blog.springbootblogapi.bussines.concretes;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.springbootblogapi.bussines.abstracts.PostService;
import com.springboot.blog.springbootblogapi.core.entity.PostResponse;
import com.springboot.blog.springbootblogapi.core.excaption.ResourceNotFoundExcaption;
import com.springboot.blog.springbootblogapi.entities.concrete.Post;
import com.springboot.blog.springbootblogapi.entities.dto.PostDto;
import com.springboot.blog.springbootblogapi.reporsitory.PostDao;

@Service
public class PostManager implements PostService {

	@Autowired
	private PostDao postDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto) {

		Post post = MapToEntity(postDto);
		Post newPost = postDao.save(post);

		PostDto postResponse = mapToDto(newPost);
		return postResponse;
	}

	@Override
	public void deletePostById(long id) {
		Post post = postDao.findById(id).orElseThrow(() -> new ResourceNotFoundExcaption("POST", "id", id));
		postDao.delete(post);

	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		Post post = postDao.findById(id).orElseThrow(() -> new ResourceNotFoundExcaption("POST", "id", id));
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());
		post.setTitle(postDto.getTitle());
		Post updatePost = postDao.save(post);
		return mapToDto(updatePost);

	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Post> posts = postDao.findAll(pageable);

		List<Post> listOfPosts = posts.getContent();
		List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElemenets(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		return postResponse;

	}

	private PostDto mapToDto(Post post) {
		PostDto postDto=modelMapper.map(post, PostDto.class);
//		PostDto postDto = new PostDto();
//		postDto.setId(post.getId());
//		postDto.setContent(post.getContent());
//		postDto.setDescription(post.getDescription());
//		postDto.setTitle(post.getTitle());
		return postDto;

	}

	private Post MapToEntity(PostDto postDto) {
		Post post=modelMapper.map(postDto, Post.class);	
//		Post post = new Post();
//		post.setContent(postDto.getContent());
//		post.setDescription(postDto.getDescription());
//		post.setTitle(postDto.getTitle());
		return post;
	}

	@Override
	public PostDto getPostById(long id) {

		Post post = postDao.findById(id).orElseThrow(() -> new ResourceNotFoundExcaption("POST", "id", id));
		return mapToDto(post);
	}

}
