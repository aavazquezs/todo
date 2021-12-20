package com.aavs.postgrado.services;

import java.util.List;

import com.aavs.postgrado.entities.User;

public interface UserService {
	public User save(User user);
	public User findById(Long id);
	public User findByUsername(String username);
	public List<User> findAll();
	public void remove(Long id);
}
