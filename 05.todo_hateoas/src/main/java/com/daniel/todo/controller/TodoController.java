package com.daniel.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/")
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
/*
    @Autowired
    private TodoEntityRepository todoEntityRepository;

    @GetMapping("users")
    public ResponseEntity<Page<TodoEntity>> getAllTodos() throws Exception {
        logger.info("/users");

        Page<TodoEntity> todoList = getByUser();
        if (todoList.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo Resource Not Found");
        return new ResponseEntity<Page<TodoEntity>>(todoList, HttpStatus.OK);
    }
*/
    

}