package org.daniel.netflix.servicegateway.controller;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Zuul API Gateway server name 설정
@FeignClient(name="server-gateway")
//@FeignClient(name ="todo-service"
@RibbonClient(name="todo-service")
public interface TodoServiceProxy {
	@RequestMapping(value = "/todo-service", method = RequestMethod.GET)
	public Object getHome();

	@RequestMapping(value = "/todo-service/random", method = RequestMethod.GET)
	public List<Integer> getRandomNumbers();

	@RequestMapping(value = "/todo-service/todos", method = RequestMethod.GET)
	public Object getAllTodos();
}