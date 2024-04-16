package com.task.todolist.serviceImp;

import java.time.LocalDate;
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
			Task task = taskRepository.findById(id).get();
			log.info("task "+task);

			if(task!=null) {
				if(newTask.getTitle()!=null) {
					task.setTitle(newTask.getTitle());
				}
				if(newTask.getDescription()!=null) {
					task.setDescription(newTask.getDescription());
				}
				if(newTask.getCompletionDate()!=null) {
					task.setCompletionDate(newTask.getCompletionDate());
				}
					return taskRepository.save(task);
					
		
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

	@Override
	public List<Task> getAllRemainningTask() {
		
		LocalDate date=LocalDate.now();
		return taskRepository.getAllRemainningTask(date);
	}

}
