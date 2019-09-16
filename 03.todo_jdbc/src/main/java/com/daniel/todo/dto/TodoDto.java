package com.daniel.todo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.daniel.todo.model.Todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String user;

    @NotBlank
    @Size(min = 1, max = 100)
    private String desc;

    @NotBlank
    @Size(min = 1, max = 40)
    private String targetDate;

    @NotBlank
    @Size(min = 1, max = 20)
    private boolean isDone;

    public Todo toEntity() {
        return new Todo(id, user, desc, targetDate, isDone);
    }
}