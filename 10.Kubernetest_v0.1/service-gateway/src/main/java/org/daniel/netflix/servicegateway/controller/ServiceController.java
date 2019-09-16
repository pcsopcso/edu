package org.daniel.netflix.servicegateway.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ServiceController {

	private Log log = LogFactory.getLog(ServiceController.class);

	@Autowired
	private TodoServiceProxy todoServiceProxy;

	// @Value("${number.service.url}")
	// private String numberServiceUrl;

	@HystrixCommand(fallbackMethod = "getDefaultResponse")
	@RequestMapping(value = "/add")
	public Long add() {
		long sum = 0;

		List<Integer> numbers = todoServiceProxy.getRandomNumbers();

		for (int number : numbers) {
			sum += number;
		}

		log.warn("Returning " + sum);

		return sum;
	}

	public Long getDefaultResponse() {
		return 10000L;
	}

	@RequestMapping(value = "/")
	public Object todos() {
		Object pages = todoServiceProxy.getAllTodos();

		log.warn("todos response");

		return pages;
	}
}