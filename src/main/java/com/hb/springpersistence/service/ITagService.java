package com.hb.springpersistence.service;

import java.util.List;
import java.util.Optional;

import com.hb.springpersistence.dto.TagDTO;
import com.hb.springpersistence.entities.Tag;

public interface ITagService {

	public Optional<Tag> getTag(Integer id);

	public Iterable<Tag> getTags();
	
	public List<TagDTO> getTagDTOs();

	public Tag save(Tag tag);

	public void delete(Integer id);

}
