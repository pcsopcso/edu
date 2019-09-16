package com.daniel.todo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import com.daniel.todo.dto.TodoDto;
import com.daniel.todo.service.TodoServiceImple;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	TodoServiceImple todoService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping("/")
	@ResponseBody
	public String home() {
		String strTemp = "Hello Todo! <br>" + getHostInfo();

		return strTemp;
	}

	@GetMapping("/todos")
	@ResponseBody
	public Object getTodos() throws Exception {
		logger.info("/todos");

		List<TodoDto> todos = todoService.retrieveTodos("daniel");

		return todos;
	}

	@GetMapping("/todos/{id}")
	@ResponseBody
	public Object getTodo(@PathVariable Long id) throws Exception {
		logger.info("/todos/{id}");

		TodoDto todo = todoService.retrieveTodo(id);

		return todo;
	}

	@GetMapping("/todos/user/{name}")
	@ResponseBody
	public ResponseEntity<List<TodoDto>> getTodo(@PathVariable String name) throws Exception {
		logger.info("/todos/user/{name}");

		List<TodoDto> todos = todoService.retrieveTodos(name);

		return new ResponseEntity<List<TodoDto>>(todos, HttpStatus.OK);
	}

  @PutMapping("/todos/todo")
  @ResponseStatus
  public ResponseEntity<TodoDto> todoUpdate(@RequestParam(value = "id") Long id,
  						   @RequestParam(value = "user") String user,
						   @RequestParam(value = "desc") String desc,
						   @RequestParam(value = "targetDate") String targetDate,
						   @RequestParam(value = "isDone") Boolean isDone) throws Exception {

		TodoDto todoDto = new TodoDto(id, user, desc, targetDate, isDone);
		todoService.updateTodo(todoDto);
   
		return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
  }


  @PostMapping("/todos/todo")
  @ResponseBody
  public ResponseEntity<TodoDto> todoAdd(@RequestParam(value = "user") String user,
  						@RequestParam(value = "desc") String desc,
  						@RequestParam(value = "targetDate") String targetDate,
  						@RequestParam(value = "isDone") Boolean isDone) throws Exception {
	
		Long id = todoService.addTodo(user, desc, targetDate, isDone);
		TodoDto todoDto = new TodoDto(id, user, desc, targetDate, isDone);

		return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
  }

	private static Object retJson(Object obj) {
		Gson gson = new Gson();

		return gson.toJson(obj);
	}

	private static String getHostInfo() {
		String hostname = System.getenv("HOSTNAME");
		if (hostname != null)
			return hostname;

		String lineStr = "";
		try {
			Process process = Runtime.getRuntime().exec("hostname");
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((lineStr = br.readLine()) != null) {
				hostname = lineStr;
			}
		} catch (IOException e) {
			e.printStackTrace();
			hostname = "";
		}
		return "HostName : " + hostname + getAddress(hostname);
	}

	private static String getAddress(String hostname) {
		String ipaddress = "";
		try {
			InetAddress address = InetAddress.getByName(hostname);
			ipaddress = " Address: " + address.getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
			System.out.println("error");
			ipaddress = "";
		}
		return ipaddress;
	}

}
