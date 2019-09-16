package com.daniel.todo.service;

import java.util.List;

import com.daniel.todo.dao.*;
import com.daniel.todo.dto.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImple implements TodoService  {

    @Autowired
    TodoDaoImple todoDao;

    @Override
    public List<Todo> getAllTodos() throws Exception {
        List<Todo> todoList = todoDao.findAll();
        return todoList;
    }

    public List<Todo> retrieveTodos(String user) throws Exception {
        List<Todo> todoList = todoDao.findByUser(user);
        return todoList;
    }

    public long addTodo(String user, String desc, String targetDate, boolean isDone) throws Exception {
        long retVal = todoDao.insert(user, desc, targetDate, isDone);
        return retVal;
    }

    public int[] addTodoList(List<Todo> todoList) throws Exception {
        int[] retVal = todoDao.insert(todoList);
        return retVal;
    }

    public Todo retrieveTodo(long id) throws Exception {
        return todoDao.findById(id);
    }

    // 01. 상품목록
    @Override
    public int updateTodo(Todo todo) throws Exception {
        return todoDao.update(todo);
    }

    @Override
    public int updateTodo(Long id, String user, String desc, String targetDate, boolean isDone) throws Exception {
        return todoDao.update(id, user, desc, targetDate, isDone);
    }

    // 04. 상품삭제
    @Override
    public void deleteTodo(Long id) throws Exception {
        todoDao.delete(id);
    }
}
