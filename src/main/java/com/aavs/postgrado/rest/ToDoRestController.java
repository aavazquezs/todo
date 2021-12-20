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
import com.aavs.postgrado.entities.ToDo;
import com.aavs.postgrado.repositories.ToDoRepository;

@RestController
@RequestMapping("/api/v1/todo")
public class ToDoRestController {
	@Autowired
	private ToDoRepository repo;
	
	@GetMapping("/{id}")
	public ToDo read(@PathVariable(name = "id") Long id) {
		return repo.findById(id).get();
	}
	
	@PostMapping
	public ToDo add(@RequestBody ToDo todo) {
		return repo.save(todo);
	}
	
	@PutMapping
	public ToDo update(@RequestBody ToDo todo) {
		return repo.save(todo);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		repo.deleteById(id);
	}
	
	@GetMapping
	public List<ToDo> findAll(){
		return repo.findAll();
	}
}
