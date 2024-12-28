package cz.uhk.kppro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cz.uhk.kppro.model.User;
import cz.uhk.kppro.service.UserService;
import cz.uhk.kppro.service.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class KpproApplication {

	private UserService userService;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public KpproApplication(UserService userService, PasswordEncoder passwordEncoder){
		this.userService =userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			addUser("admin@law.com", "heslo", "ADMIN");
			addUser("user@law.com", "heslo", "USER");
		};
	}

	private void addUser(String email, String password, String role) {
		if (userService.findByEmail(email) == null) {
			User user = new User();
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode(password));
			
			user.setRole("ROLE_" + role);
			userService.save(user);
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(KpproApplication.class, args);
	}

}
