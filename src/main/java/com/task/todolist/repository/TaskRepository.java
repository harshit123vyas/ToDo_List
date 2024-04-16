package com.task.todolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.todolist.entity.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	public Optional<Task> findByTitle(String title);
}
