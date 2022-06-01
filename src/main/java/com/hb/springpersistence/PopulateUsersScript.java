package com.hb.springpersistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.hb.springpersistence.entities.InternalUser;
import com.hb.springpersistence.repositories.InternalUserRepository;

//@Component
public class PopulateUsersScript implements CommandLineRunner {

	@Autowired
	public InternalUserRepository repository;

	@Override
	public void run(String... args) throws Exception {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		InternalUser admin = new InternalUser();
		admin.setUsername("admin");
		admin.setPassword(encoder.encode("admin"));
		repository.save(admin);

		InternalUser user = new InternalUser();
		user.setUsername("user");
		user.setPassword(encoder.encode("user"));
		repository.save(user);

	}

}
