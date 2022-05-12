package com.hb.springpersistence.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.springpersistence.entities.PostComment;
import com.hb.springpersistence.repositories.CommentRepository;

@Service
@Transactional
public class CommentService implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Optional<PostComment> getComment(Integer id) {
		return commentRepository.findById(id);
	}

	@Override
	public Iterable<PostComment> getComments() {
		return commentRepository.findAll();
	}

	@Override
	public PostComment save(PostComment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void delete(Integer id) {
		commentRepository.deleteById(id);
	}

}
