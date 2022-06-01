package com.hb.springpersistence;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hb.springpersistence.entities.InternalRole;
import com.hb.springpersistence.entities.InternalUser;
import com.hb.springpersistence.repositories.InternalRoleRepository;
import com.hb.springpersistence.repositories.InternalUserRepository;

//@Component
public class PopulateUsersScript implements CommandLineRunner {

	@Autowired
	public InternalUserRepository userRepository;

	@Autowired
	public InternalRoleRepository roleRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//		InternalUser admin = new InternalUser();
//		admin.setUsername("admin");
//		admin.setPassword(encoder.encode("admin"));
//		repository.save(admin);
//
//		InternalUser user = new InternalUser();
//		user.setUsername("user");
//		user.setPassword(encoder.encode("user"));
//		repository.save(user);

//		InternalUser admin = userRepository.findById(1).get();
//		InternalRole roleAdmin = roleRepository.findById(1).get();
//		admin.getRoles().add(roleAdmin);
//		userRepository.save(admin);
//
//		InternalUser user = userRepository.findById(2).get();
//		InternalRole roleUser = roleRepository.findById(2).get();
//		user.getRoles().add(roleUser);
//		userRepository.save(user);
	}

}
