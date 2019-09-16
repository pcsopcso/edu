package com.daniel.todo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TodoEntity implements Serializable {

    private static final long serialVersionUID = 8362736640369461848L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long todoID;

    @Column(nullable = false)
    @JsonProperty
    private String user;

    @Column(nullable = false)
    @JsonProperty
    private String desc;

    @Column(nullable = false)
    @JsonProperty
    private String targetDate;

    @Column(nullable = false)
    @JsonProperty
    private boolean isDone;
}
