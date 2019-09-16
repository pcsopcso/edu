package com.daniel.todo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TodoEntity implements Serializable {

    private static final long serialVersionUID = 8362736640369461848L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoID;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private String desc;

    @Column(nullable = false)
    private String targetDate;

    @Column(name = "done", nullable = false)
    private boolean isDone;
}
