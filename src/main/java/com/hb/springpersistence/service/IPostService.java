package com.hb.springpersistence.service;

import java.util.List;
import java.util.Optional;

import com.hb.springpersistence.dto.PostDTO;
import com.hb.springpersistence.entities.Post;

public interface IPostService {

	public Optional<Post> getPost(Integer id);

	public Iterable<Post> getPostsWithProxies();
	
	public Iterable<Post> getPosts();
	
	public List<PostDTO> getPostDTOs();

	public Post save(Post post);

	public void delete(Integer id);

	public void mapPostTag(Integer postId, Integer tagId);
}
