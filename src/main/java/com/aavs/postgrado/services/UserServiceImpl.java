package com.aavs.postgrado.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aavs.postgrado.entities.User;
import com.aavs.postgrado.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repo;
	
	
	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		return repo.save(user);
	}

	@Override
	public User findById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public User findByUsername(String username) {
		return repo.findByUsername(username).orElse(null);
	}

	@Override
	public List<User> findAll() {
		return repo.findAll();
	}

	@Override
	public void remove(Long id) {
		repo.deleteById(id);
	}

	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
