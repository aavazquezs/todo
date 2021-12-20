package com.aavs.postgrado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aavs.postgrado.entities.User;
import com.aavs.postgrado.repositories.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/list")
	private String index(Model model) {
		List<User> users = repository.findAll();
		model.addAttribute("users", users);
		model.addAttribute("pagActual", "user");
		return "user-list";
	}
}
