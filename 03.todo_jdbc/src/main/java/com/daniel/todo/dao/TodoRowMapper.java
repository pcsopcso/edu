package com.daniel.todo.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.daniel.todo.dto.Todo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TodoRowMapper implements RowMapper<Todo> {

    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Todo todo = new Todo(rs.getLong("todoID"), rs.getString("user"), rs.getString("desc"),
                rs.getString("targetDate"), rs.getBoolean("isDone"));
        
        return todo;
    }

    public static <T> T convertValue(Object fromValue, TypeReference<?> toValueTypeRef)
    {
        T retval = null;
        ObjectMapper mapper = new ObjectMapper();
        //파라미터 Map에서 DTO에 들어있지 않는 변수가 있어도 무시함.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
        retval =  mapper.convertValue(fromValue, toValueTypeRef);
        return retval;
    }
}