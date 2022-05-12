package com.hb.springpersistence.service;

import java.util.Optional;

import com.hb.springpersistence.entities.PostComment;

public interface ICommentService {

	public Optional<PostComment> getComment(Integer id);

	public Iterable<PostComment> getComments();

	public PostComment save(PostComment comment);

	public void delete(Integer id);

}
