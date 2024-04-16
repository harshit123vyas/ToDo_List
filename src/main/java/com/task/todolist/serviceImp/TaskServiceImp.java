package com.task.todolist.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.todolist.entity.Task;
import com.task.todolist.repository.TaskRepository;
import com.task.todolist.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskServiceImp implements TaskService{
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Task> getAllTask() {
	
		try {
			List<Task> taskList=taskRepository.findAll();
			log.info("list of tasks "+taskList);
			if(!taskList.isEmpty()) {
				return taskList;
			}
			
		}catch (Exception e) {
			log.error("exception "+ e);
		}
		
			return null;
	
	}

	@Override
	public Task getTaskById(Integer id) {
		
		try {
			Optional<Task> task=taskRepository.findById(id);
			if(task.isPresent()) {
				return task.get();
			}
		} catch (Exception e) {
			log.error("exception "+e);
		}
		
		return null;
	}

	@Override
	public Task addTask(Task task) {
		try {
			
			Optional<Task> existingTask = taskRepository.findByTitle(task.getTitle()); 
			log.info("existingTask "+existingTask);
			if(existingTask.isEmpty()) {
				return taskRepository.save(task);
			}
			
		} catch (Exception e) {
			log.error("exception "+e);
		}
		
		return null;	
		}
		
	
	@Override
	public Task updateTask(Task newTask, Integer id) {
		try {
			Optional<Task> task = taskRepository.findById(id);
			log.info("task "+task);
			if(task.isPresent()) {
				if(!newTask.getTitle().equals(null)) {
					task.get().setTitle(newTask.getTitle());
				}
				if(!newTask.getDescription().equals(null)) {
					task.get().setDescription(newTask.getDescription());
				}
				return taskRepository.save(task.get());
			}
			
		} catch (Exception e) {
			log.error("exception "+e);
		}
	
		return null;
	}

	@Override
	public boolean deleteTaskById(Integer id) {
		
		try {
			Optional<Task> task=taskRepository.findById(id);
			if(task.isPresent()) {
				taskRepository.deleteById(id);
				return true;
			}
		} catch (Exception e) {
			log.error("exception "+e);
		}
		return false;
		
	}

}
