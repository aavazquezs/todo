package com.aavs.postgrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aavs.postgrado.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	
}
