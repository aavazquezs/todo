package com.aavs.postgrado.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aavs.postgrado.entities.Tag;
import com.aavs.postgrado.repositories.TagRepository;

@RestController
@RequestMapping("/api/v1/tag")
public class TagRestController {
	@Autowired
	private TagRepository repo;
	
	@GetMapping("/{id}")
	public Tag read(@PathVariable(name = "id") Long id) {
		return repo.findById(id).get();
	}
	
	@PostMapping
	public Tag add(@RequestBody Tag tag) {
		return repo.save(tag);
	}
	
	@PutMapping
	public Tag update(@RequestBody Tag tag) {
		return repo.save(tag);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		repo.deleteById(id);
	}
	
	@GetMapping
	public List<Tag> findAll(){
		return repo.findAll();
	}
}
