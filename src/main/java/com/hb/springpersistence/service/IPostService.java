package com.hb.springpersistence.service;

import java.util.List;
import java.util.Optional;

import com.hb.springpersistence.dto.PostDTO;
import com.hb.springpersistence.entities.Post;

public interface IPostService {

	public Optional<Post> getPost(Integer id);

	public Iterable<Post> getPostsWithProxies();
	
	public Iterable<Post> getPosts();
	
	public PostDTO getPostDTO(Integer id);
	
	public List<PostDTO> getPostDTOs();

	public PostDTO save(PostDTO post);

	public void delete(Integer id);

	public void mapPostTag(Integer postId, Integer tagId);
}
