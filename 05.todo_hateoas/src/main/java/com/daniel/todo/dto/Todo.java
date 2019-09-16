package com.daniel.todo.dto;

import java.io.Serializable;

import com.daniel.todo.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Todo implements Serializable {

  private static final long serialVersionUID = 8362776488369461848L;

  private Long todoID;

  // @NotBlank
  // @Size(min = 1, max = 50)
  private String user;

  // @NotBlank
  // @Size(min = 1, max = 100)
  private String desc;

  // @NotBlank
  // @Size(min = 1, max = 40)
  private String targetDate;

  // @NotBlank
  // @Size(min = 1, max = 20)
  private boolean isDone;

  public TodoEntity toEntity() {
    return new TodoEntity(todoID, user, desc, targetDate, isDone);
  }

}