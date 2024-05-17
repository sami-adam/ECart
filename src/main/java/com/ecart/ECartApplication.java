package com.ecart;

import com.ecart.entity.Role;
import com.ecart.entity.User;
import com.ecart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ECartApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(ECartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin = userRepository.findUserByRole(Role.ADMIN);
		if (admin == null){
			User user = new User();

			user.setEmail("admin@ecart.com");
			user.setUsername("admin");
			user.setFirstName("Admin");
			user.setLastName("Admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("qLUftio3"));
			userRepository.save(user);
		}
	}
}
