package com.hb.springpersistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hb.springpersistence.entities.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

}
