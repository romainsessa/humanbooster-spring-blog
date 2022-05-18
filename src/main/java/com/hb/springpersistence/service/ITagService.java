package com.hb.springpersistence.service;

import java.util.List;
import java.util.Optional;

import com.hb.springpersistence.dto.TagDTO;
import com.hb.springpersistence.entities.Tag;

public interface ITagService {

	public List<TagDTO> getTagDTOs();

	public TagDTO getTagDTO(Integer id);

	public void delete(Integer id);

	public TagDTO save(TagDTO tag);

}
