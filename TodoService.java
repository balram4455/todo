package com.balram.firstwebapp.firstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
@Service
public class TodoService {
	private static List<Todo> todos=new ArrayList<>();
	private static int todoCount=0;	
	static {
		todos.add(new Todo(++todoCount,"balram","learn Aws", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todoCount,"balram","learn DevOps", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todoCount,"balram","learn fullStack", LocalDate.now().plusYears(3), false));
	}
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	public static void addTodo(String username,String description,LocalDate targetdate,boolean done) {
		Todo todo=new Todo(++todoCount,username,description,targetdate,done);
		todos.add(todo);
	}
	
	public static void deleteById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate=todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}

	
	public static Todo findById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate=todo -> todo.getId()==id;
		Todo todo=todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	public static void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
		
	}

}
