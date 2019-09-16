package com.daniel.todo.dao;

import java.util.List;
import com.daniel.todo.dto.Todo;

public interface TodoDao {

    public Long insert(Todo todo) throws Exception;

    public Long insert(String user, String desc, String targetDate, boolean isDone) throws Exception;

    public int[] insert(List<Todo> todoList) throws Exception;

    public int update(Todo todo) throws Exception;

    public int update(Long id, String user, String desc, String targetDate, boolean isDone) throws Exception;

    public int delete(Long id) throws Exception;

    public Todo findById(long id) throws Exception;

    public Todo findByIdDesc() throws Exception;

    public List<Todo> findByUser(String user) throws Exception;

    public List<Todo> findAll() throws Exception;
}