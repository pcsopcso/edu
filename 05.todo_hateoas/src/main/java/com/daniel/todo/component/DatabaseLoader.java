/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.daniel.todo.component;

import java.util.Arrays;

import com.daniel.todo.dao.TodoEntityRepository;
import com.daniel.todo.model.TodoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Pre-load some data using a Spring Boot {@link CommandLineRunner}.
 *
 * @author Daniel
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

	/**
	 * Use Spring to inject a {@link UserRepository} that can then load data. Since
	 * this will run only after the app is operational, the database will be up.
	 *
	 * @param repository
	 */
	@Autowired
	TodoEntityRepository todoRepository;

	@Override
	public void run(String... args) throws Exception {

		Arrays.asList(new TodoEntity(-1L, "daniel", "메일", "2019-09-27 14:30:00", false),
				new TodoEntity(-1L, "park", "결재", "2019-09-27 14:30:00", false),
				new TodoEntity(-1L, "daniel", "일정", "2019-09-27 14:30:00", false))
				.forEach(todo -> todoRepository.save(todo));

		todoRepository.findAll().forEach(System.out::println);
	}
}
