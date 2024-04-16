package com.task.todolist.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.todolist.entity.Task;
import com.task.todolist.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public ResponseEntity<List<Task>> findAllTask(){
		log.info("fetching all tasks");
		List<Task> taskList = taskService.getAllTask();
		log.info("taskList "+taskList);
		if(!taskList.isEmpty()) {
			return new ResponseEntity<List<Task>>(taskList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Task>>(taskList,HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Integer id){
		log.info("get Task By Id");
		Task task= taskService.getTaskById(id);
		if(task!=null) {
			return new ResponseEntity<Task>(task,HttpStatus.FOUND);
		}
		else {
			return  new ResponseEntity<Task>(task,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Task> addTask(@RequestBody Task task){
		log.info("adding new task");
		Task addedTask = taskService.addTask(task);
		if(task!=null) {
			return new ResponseEntity<Task>(addedTask,HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Task>(addedTask,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@RequestBody Task task,@PathVariable Integer id){
		log.info("updating the task");
		Task updatedTask=taskService.getTaskById(id);
		if(updatedTask!=null) {
			return new ResponseEntity<Task>(taskService.updateTask(updatedTask, id),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Task>(updatedTask,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTaskById(@PathVariable Integer id){
		log.info("deleting task by id ");
		if(taskService.deleteTaskById(id)) {
			return new ResponseEntity<String>("Task deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Task not with given id not exist ",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/date")
	public ResponseEntity<List<Task>> getByDate(){
		List<Task> taskList = taskService.getAllRemainningTask();
		log.info("taskList "+taskList);
		if(!taskList.isEmpty()) {
			return new ResponseEntity<List<Task>>(taskList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Task>>(taskList,HttpStatus.NO_CONTENT);
		}
	}
	

}
