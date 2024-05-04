package com.balram.firstwebapp.firstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
//@SessionAttributes("name")
public class TodoController {
	private TodoService todoservice;
	public TodoController(TodoService todoservice) {
		super();
		this.todoservice = todoservice;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		
		List<Todo>todos=todoservice.findByUsername("balram");
		model.addAttribute("todos",todos);
		return "listTodos";
	}

	
	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String shownewTodos(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo = new Todo(0, username, " ", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "addtodo";
	}
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addnewTodos(ModelMap model,@Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		String username = (String)model.get("name");
		TodoService.addTodo(username,todo.getDescription(),todo.getTargetdate(),false);
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodos(int id) {
		TodoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String updateTodos(@RequestParam int id,ModelMap model) {
		Todo todo=TodoService.findById(id);
		model.addAttribute("todo",todo);
		return "addtodo";
	}
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodos(ModelMap model,@Valid Todo todo,BindingResult result) {
		if(result.hasErrors()) {
			return "addtodo";
		}
		String username = (String)model.get("name");
		todo.setUsername(username);
		TodoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	
}
