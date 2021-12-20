package com.aavs.postgrado.controllers;

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
import com.aavs.postgrado.repositories.TagRepository;

@Controller
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	private TagRepository repository;
	
	@GetMapping("/list")
	private String lista(Model model) {
		List<Tag> tags = repository.findAll();
		model.addAttribute("tags", tags);
		model.addAttribute("pagActual", "tag");
		return "tag-list";
	}
	
	@GetMapping("/edit/{id}")
	private String edit(Model model, @PathVariable String id) {
		model.addAttribute("pagActual", "tag");
		Tag actual = repository.getById(Long.parseLong(id));
		model.addAttribute("tag", actual);
		return "tag-edit";
	}
	
	@PostMapping("/edit")
	private String editing(Model model, Tag tag) {
		repository.save(tag);
		return "redirect:/tag/list";
	}
	
	@GetMapping("/remove/{id}")
	private String remove(Model model, @PathVariable String id) {
		model.addAttribute("pagActual", "tag");
		Long tagId = Long.parseLong(id);
		Tag tagToRemove = repository.getById(tagId);
		repository.delete(tagToRemove);
		return "redirect:/tag/list";
	}
	
	@GetMapping("/add")
	private String add(Model model) {
		model.addAttribute("pagActual", "tag");
		return "tag-add";
	}
	
	@PostMapping("/add")
	private String adding(
			@RequestParam(value = "nombre", required = true) String nombre, 
			@RequestParam(value= "color", required = true) String color,
			Model model) {
		Tag nueva = Tag.builder()
				.name(nombre)
				.color(color)
				.build();
		nueva = repository.save(nueva);
		return "redirect:/tag/list";
	}
}
