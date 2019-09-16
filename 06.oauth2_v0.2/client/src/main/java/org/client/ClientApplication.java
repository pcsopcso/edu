package org.client;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.client.constrant.UserRole;
import org.client.entity.UserInfo;
import org.client.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner, ServletContextInitializer {

	@Autowired
	private UserInfoRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		UserInfo user1 = new UserInfo();
		user1.setId(1l);
		user1.setUsername("admin");
		user1.setPassword(passwordEncoder.encode("1234"));
		user1.setRole(UserRole.ROLE_ADMIN);

		repository.save(user1);

		UserInfo user2 = new UserInfo();

		user2.setId(2l);
		user2.setUsername("pcso");
		user2.setPassword(passwordEncoder.encode("1234"));
		user2.setRole(UserRole.ROLE_USER);

		repository.save(user2);

		UserInfo user3 = new UserInfo();

		user3.setId(3l);
		user3.setUsername("park");
		user3.setPassword(passwordEncoder.encode("1234"));
		user3.setRole(UserRole.ROLE_USER);

		repository.save(user3);

	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.getSessionCookieConfig().setName("clientsession");
	}

}
