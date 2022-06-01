package com.hb.springpersistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hb.springpersistence.entities.InternalRole;

@Repository
public interface InternalRoleRepository extends CrudRepository<InternalRole, Integer> {

}
