package com.hb.springpersistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.springpersistence.dto.PostDTO;
import com.hb.springpersistence.dto.transformers.TransformerFactory;
import com.hb.springpersistence.entities.Post;
import com.hb.springpersistence.entities.Tag;
import com.hb.springpersistence.repositories.PostRepository;
import com.hb.springpersistence.repositories.TagRepository;

@Service
@Transactional
public class PostService implements IPostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private TagRepository tagRepository;

	public Optional<Post> getPost(Integer id) {
		return postRepository.findById(id);
	}

	public Iterable<Post> getPostsWithProxies() {
		return postRepository.findAll();
	}

	public Iterable<Post> getPosts() {
		Iterable<Post> posts = postRepository.findAll();
		for (Post post : posts) {
			post.getComments().forEach(comment -> Hibernate.unproxy(comment));
			post.getTags().forEach(tag -> Hibernate.unproxy(tag));
		}
		return posts;
	}

	public List<PostDTO> getPostDTOs() {

		Iterable<Post> posts = postRepository.findAll();
		List<PostDTO> postDTOs = new ArrayList<>();

		for (Post p : posts) {
			PostDTO pDTO = TransformerFactory.getPostTransformer().transform(p);
			postDTOs.add(pDTO);
		}

		return postDTOs;
	}

	public PostDTO getPostDTO(Integer id) {
		Post post = postRepository.findById(id).get();
		PostDTO postDTO = TransformerFactory.getPostTransformer().transform(post);
		return postDTO;
	}

	public PostDTO save(PostDTO post) {
		Post entityPost = 
				TransformerFactory.getPostTransformer().transform(post);

		Post entity = postRepository.save(entityPost);

		return TransformerFactory.getPostTransformer().transform(entity);
	}

	public void delete(Integer id) {
		postRepository.deleteById(id);
	}

	public void mapPostTag(Integer postId, Integer tagId) {
		Post p = getPost(postId).get();
		Tag t = tagRepository.findById(tagId).get();
		p.addTag(t);
		postRepository.save(p);
	}

}