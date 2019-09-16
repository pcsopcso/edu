package com.daniel.todo.service;

import java.util.List;

import com.daniel.todo.dto.Todo;

interface TodoService {

	public List<Todo> getAllTodos() throws Exception;

	public List<Todo> retrieveTodos(String user) throws Exception;

	public Todo retrieveTodo(long id) throws Exception;

	public long addTodo(String user, String desc, String targetDate, boolean isDone) throws Exception;

	public int[] addTodoList(List<Todo> todoDtoList) throws Exception;

	public int updateTodo(Todo todoDto) throws Exception;

	public int updateTodo(Long id, String user, String desc, String targetDate, boolean isDone) throws Exception;

	public void deleteTodo(Long id) throws Exception;
}