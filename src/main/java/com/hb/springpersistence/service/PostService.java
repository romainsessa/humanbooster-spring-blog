package com.hb.springpersistence.service;

import java.util.ArrayList;
import java.util.List;
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

	/**
	 * Note @Romain : je laisse cette méthode pour exemple pédagogique - n'a pas
	 * d'intérêt métier dans le projet. Cette méthode résout les proxys avant la fin
	 * de la transaction Cela permet dans certains scénarions de prevenir l'erreur
	 * LazyLoadingException Mais attention, cela charge toutes les données donc les
	 * performances peuvent être impactées.
	 * 
	 * @return Une liste des entités avec les proxys du Lazy loading résolus.
	 */
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
		return TransformerFactory.getPostTransformer().transform(post);
	}

	public PostDTO save(PostDTO post) {
		Post entityPost = TransformerFactory.getPostTransformer().transform(post);
		Post entity = postRepository.save(entityPost);
		return TransformerFactory.getPostTransformer().transform(entity);
	}

	public void delete(Integer id) {
		postRepository.deleteById(id);
	}

	public void mapPostTag(Integer postId, Integer tagId) {
		Post p = postRepository.findById(postId).get();
		Tag t = tagRepository.findById(tagId).get();
		p.addTag(t);
		postRepository.save(p);
	}

	public void unmapPostTag(Integer postId, Integer tagId) {
		Post p = postRepository.findById(postId).get();
		Tag t = tagRepository.findById(tagId).get();
		p.removeTag(t);
		postRepository.save(p);
	}

}