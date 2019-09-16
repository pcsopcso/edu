package com.daniel.todo.service;

import java.util.List;

import com.daniel.todo.dao.*;
import com.daniel.todo.model.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImple implements TodoService {

    @Autowired
    TodoServiceImple todoService;

    @Autowired
    TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() throws Exception {

        List<Todo> todoList = todoRepository.findAll();
        return todoList;
    }

    public List<Todo> retrieveTodos(String user) throws Exception {
        List<Todo> todoList = todoRepository.findByUser(user);
        return todoList;
    }

    public Todo addTodo(String user, String desc, String targetDate, boolean isDone) throws Exception {
        Todo todo = new Todo(-1L, user, desc, targetDate, isDone);
        return todoRepository.save(todo);
    }

    public List<Todo> addTodoList(List<Todo> todoList) throws Exception {
        return todoRepository.saveAll(todoList);
    }

    public Todo retrieveTodo(long todoID) throws Exception {
        return todoRepository.findById(todoID).get();
    }

    // 01. 상품목록
    @Override
    public Todo updateTodo(Todo todo) throws Exception {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Long todoID, String user, String desc, String targetDate, boolean isDone) throws Exception {
        Todo todo = new Todo(todoID, user, desc, targetDate, isDone);
        return todoRepository.save(todo);
    }

    // 04. 상품삭제
    @Override
    public void deleteTodo(Long id) throws Exception {
        todoRepository.deleteById(id);
    }
}
