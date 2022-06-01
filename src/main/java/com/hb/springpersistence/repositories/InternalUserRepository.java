package com.hb.springpersistence.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hb.springpersistence.entities.InternalUser;

public interface InternalUserRepository extends CrudRepository<InternalUser, Integer> {
	
	public InternalUser findByUsername(String username);

}
