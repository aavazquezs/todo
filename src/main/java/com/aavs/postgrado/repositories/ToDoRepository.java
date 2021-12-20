package com.aavs.postgrado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aavs.postgrado.entities.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{

}
