package com.hb.springpersistence.service;

import java.util.Optional;

import com.hb.springpersistence.entities.Tag;

public interface ITagService {

	public Optional<Tag> getTag(Integer id);

	public Iterable<Tag> getTags();

	public Tag save(Tag tag);

	public void delete(Integer id);

}
