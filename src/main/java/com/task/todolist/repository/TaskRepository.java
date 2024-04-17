package com.task.todolist.repository;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task.todolist.entity.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	public Optional<Task> findByTitle(String title);
	

    @Query("select t from Task t where t.completionDate >= :presentDate")
    List<Task> getAllRemainningTask(@Param("presentDate") LocalDate creationDate);
    
    public List<Task> findTaskByCreationDate(LocalDate creationDate) ;
    
    
}
