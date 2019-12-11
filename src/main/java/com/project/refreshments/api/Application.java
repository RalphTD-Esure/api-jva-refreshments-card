package com.project.refreshments.api;

import java.util.stream.Stream;

import com.project.refreshments.entities.User;
import com.project.refreshments.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application
{
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}

		@Bean
		CommandLineRunner init(UserRepository userRepository) {
			return args -> {
				Stream.of("Ralph", "Quynh", "Jacob", "Andy", "Graeme").forEach(name -> {
					User user = new User(name, name.toLowerCase() + "@domain.com");
					userRepository.save(user);
				});
				userRepository.findAll().forEach(System.out::println);
			};
		}

}
