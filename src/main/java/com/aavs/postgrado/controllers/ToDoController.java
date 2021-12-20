package com.aavs.postgrado.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aavs.postgrado.entities.Tag;
import com.aavs.postgrado.entities.ToDo;
import com.aavs.postgrado.repositories.TagRepository;
import com.aavs.postgrado.repositories.ToDoRepository;

@Controller
@RequestMapping("/todo")
public class ToDoController {
	
	@Autowired
	private ToDoRepository repository;
	
	@Autowired
	private TagRepository tagRepo;
	
	@GetMapping("/list")
	private String list(Model model) {
		List<ToDo> todos = repository.findAll();
		model.addAttribute("todos", todos);
		model.addAttribute("pagActual", "todo");
		return "todo-list";
	}
	
	@GetMapping("/edit/{id}")
	private String edit(Model model, @PathVariable String id) {
		model.addAttribute("pagActual", "todo");
		ToDo actual = repository.getById(Long.parseLong(id));
		model.addAttribute("todo", actual);
		model.addAttribute("tags", tagRepo.findAll());
		return "todo-edit";
	}
	
	@PostMapping("/edit")
	private String editing(
			@RequestParam(value="tags", required=true) List<String> tagsId,
			@RequestParam(value="isDone", required = false) String isDone, 
			Model model, 
			ToDo todo) {
		//System.out.println(todo);
		//System.out.println(isDone);
		List<Tag> tags = new ArrayList<>();
		for (String tagId : tagsId) {
			Long id = Long.parseLong(tagId);
			Tag tag = tagRepo.getById(id);
			tags.add(tag);
		}
		todo.setTags(tags);
		
		if(isDone == null) {
			todo.setDone(false);
		}else {
			todo.setDone((isDone.equals("on")||isDone.equals("true"))?true:false);
		}
		repository.save(todo);
		return "redirect:/todo/list";
	}
	
	@GetMapping("/remove/{id}")
	private String remove(Model model, @PathVariable String id) {
		model.addAttribute("pagActual", "todo");
		Long todoID = Long.parseLong(id);
		ToDo todoToRemove = repository.getById(todoID);
		repository.delete(todoToRemove);
		return "redirect:/todo/list";
	}
	
	@GetMapping("/add")
	private String add(Model model) {
		model.addAttribute("pagActual", "todo");
		ToDo nuevo = ToDo.builder().build();
		model.addAttribute("todo", nuevo);
		List<Tag> tags = tagRepo.findAll();
		model.addAttribute("tags", tags);
		return "todo-add";
	}
	
	@PostMapping("/add")
	private String adding(/*
			@RequestParam(value = "description", required = true) String description, 
			@RequestParam(value= "dueDate", required = true) String dueDate,*/
			@RequestParam(value="tags", required=true) List<String> tagsId,
			Model model, ToDo nuevo) {
		model.addAttribute("pagActual", "todo");
		nuevo.setCreateAt(LocalDate.now());
		List<Tag> tags = new ArrayList<>();
		for (String tagId : tagsId) {
			Long id = Long.parseLong(tagId);
			Tag tag = tagRepo.getById(id);
			tags.add(tag);
		}
		nuevo.setTags(tags);
		nuevo = repository.save(nuevo);
		
		
		model.addAttribute("todo", nuevo);
		return "redirect:/todo/list";
	}
}
