package com.hb.springpersistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hb.springpersistence.entities.PostComment;

@Repository
public interface CommentRepository extends CrudRepository<PostComment, Integer> {

}
