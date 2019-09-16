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
package org.resourceserver.config;

import java.util.Arrays;

import org.resourceserver.controller.EmployeeRepository;
import org.resourceserver.controller.ManagerRepository;
import org.resourceserver.entity.Employee;
import org.resourceserver.entity.Manager;
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
	 * Use Spring to inject a {@link UserRepository} that can then load data. Since this will run
	 * only after the app is operational, the database will be up.
	 *
	 * @param repository
	 */
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
        	Manager daniel = managerRepository.save(new Manager("Daniel"));
            Arrays.asList(
                    new Employee("토니 스타크", "아이언맨", daniel),
                    new Employee("브루스 배너", "헐크", daniel),
                    new Employee("스콧", "앤트맨", daniel)
            )
            .forEach(account -> employeeRepository.save(account));
            employeeRepository.findAll().forEach(System.out::println);
            managerRepository.save(daniel);
            
        	Manager catain = managerRepository.save(new Manager("Catain America"));
        	Arrays.asList(
                    new Employee("토르", "천둥의신", catain),
                    new Employee("나타샤", "블랙위도우", catain),
                    new Employee("클린트 바튼", "호크아이", catain)
            )
        	.forEach(account -> employeeRepository.save(account));
            employeeRepository.findAll().forEach(System.out::println);
            managerRepository.save(catain);
    }
}
