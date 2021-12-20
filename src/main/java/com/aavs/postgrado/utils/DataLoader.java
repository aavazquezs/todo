package com.aavs.postgrado.utils;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aavs.postgrado.entities.Tag;
import com.aavs.postgrado.entities.ToDo;
import com.aavs.postgrado.entities.User;
import com.aavs.postgrado.repositories.TagRepository;
import com.aavs.postgrado.repositories.ToDoRepository;
import com.aavs.postgrado.repositories.UserRepository;

@Configuration
public class DataLoader {
	
	@Autowired
	private TagRepository tagRepository;
	@Autowired
	private ToDoRepository toDoRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	InitializingBean sendDatabase() {
		return ()->{
			Tag tag1 = Tag.builder()
					.name("Facultad")
					.color("C8C6D7")
					.build(); 
			Tag tag2 = Tag.builder()
					.name("Personal")
					.color("A0D2DB")
					.build(); 
			Tag tag3 = Tag.builder()
					.name("Doctorado")
					.color("04A777")
					.build();
			tag1 = tagRepository.save(tag1);
			tag2 = tagRepository.save(tag2);
			tag3 = tagRepository.save(tag3);
			System.out.println("-------TAGS--------");
			System.out.println(tag1);
			System.out.println(tag2);
			System.out.println(tag3);
			
			ToDo todo1 = ToDo.builder()
					.description("Comprar los mandados")
					.dueDate(LocalDate.now())
					.createAt(LocalDate.now())
					.tags(Arrays.asList(tag2))
					.build();
			todo1 = toDoRepository.save(todo1);
			
			System.out.println("-------TODOs--------");
			System.out.println(todo1);

			User user1 = User.builder()
					.name("User1")
					.username("user1")
					.password("password_user1")
					.todos(Arrays.asList(todo1))
					.active(true)
					.rol("USER")
					.build();
			user1 = userRepository.save(user1);
			
			System.out.println("-------USERS--------");
			System.out.println(user1);
		};
	}
}
