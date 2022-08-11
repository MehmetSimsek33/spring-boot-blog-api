package com.springboot.blog.springbootblogapi.bussines.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.springbootblogapi.bussines.abstracts.CommentService;
import com.springboot.blog.springbootblogapi.core.excaption.BlogAPIExcaption;
import com.springboot.blog.springbootblogapi.core.excaption.ResourceNotFoundExcaption;
import com.springboot.blog.springbootblogapi.entities.concrete.Comment;
import com.springboot.blog.springbootblogapi.entities.concrete.Post;
import com.springboot.blog.springbootblogapi.entities.dto.CommentDto;
import com.springboot.blog.springbootblogapi.entities.dto.PostDto;
import com.springboot.blog.springbootblogapi.reporsitory.CommentDao;
import com.springboot.blog.springbootblogapi.reporsitory.PostDao;

@Service
public class CommentManager implements CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ModelMapper modelMapper;

	// REVİEWW
	@Autowired
	private PostDao postDao;

	@Override
	public CommentDto createCommnet(long postId, CommentDto commentDto) {
		Comment comment = MapToEntity(commentDto);

		Post post = postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundExcaption("Post", "id", postId));
		comment.setPost(post);

		Comment newComment = commentDao.save(comment);
		return mapToDto(newComment);

	}

	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//		commentDto.setId(comment.getId());
//		commentDto.setBody(comment.getBody());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
		return commentDto;

	}

	private Comment MapToEntity(CommentDto commentDto) {
		Comment comment = modelMapper.map(commentDto, Comment.class);
//		Comment comment = new Comment();
//		comment.setId(commentDto.getId());
//		comment.setEmail(commentDto.getEmail());
//		comment.setName(commentDto.getName());
//		comment.setBody(commentDto.getBody());
		return comment;
	}

	@Override
	public List<CommentDto> getCommentByPostId(long postId) {

		List<Comment> comments = commentDao.findByPostId(postId);
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		Post post = postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundExcaption("Post", "id", postId));

		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundExcaption("Post", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIExcaption(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		return mapToDto(comment);

	}

	@Override
	public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
		Post post = postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundExcaption("Post", "id", postId));
		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundExcaption("Post", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIExcaption(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		comment.setBody(commentRequest.getBody());
		comment.setEmail(commentRequest.getEmail());
		comment.setName(commentRequest.getName());

		Comment updateComment = commentDao.save(comment);
		return mapToDto(updateComment);
	}

	@Override
	public void deleteComment(Long postId, long commentId) {
		Post post = postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundExcaption("Post", "id", postId));
		Comment comment = commentDao.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundExcaption("Post", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIExcaption(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		commentDao.delete(comment);

	}
}
