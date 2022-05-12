package com.hb.springpersistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.springpersistence.dto.TagDTO;
import com.hb.springpersistence.dto.transformers.TagTransformer;
import com.hb.springpersistence.dto.transformers.TransformerFactory;
import com.hb.springpersistence.entities.Post;
import com.hb.springpersistence.entities.Tag;
import com.hb.springpersistence.repositories.TagRepository;

@Service
@Transactional
public class TagService implements ITagService {

	@Autowired
	private TagRepository tagRepository;
	
	@Override
	public Optional<Tag> getTag(Integer id) {
		return tagRepository.findById(id);
	}

	@Override
	public Iterable<Tag> getTags() {
		return tagRepository.findAll();
	}
	
	@Override
	public List<TagDTO> getTagDTOs() {
		Iterable<Tag> tags = tagRepository.findAll();
		List<TagDTO> tagDTOs = new ArrayList<>();
		for(Tag tag : tags) {
			TagDTO tagDTO = TransformerFactory.getTagTransformer().transform(tag);
			tagDTOs.add(tagDTO);			
		}		
		return tagDTOs;
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public void delete(Integer id) {
		Tag t = getTag(id).get();
		List<Post> posts = new ArrayList<>();
		posts.addAll(t.getPosts());
		for(Post post : posts) {
			t.removePost(post);
		}		
		tagRepository.deleteById(id);
	}

}
