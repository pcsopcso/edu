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
package org.resourceserver.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Daniel
 */
@Entity
@AllArgsConstructor //추가 모든 필드가 있는 생성자를 만든다.
@NoArgsConstructor(access = AccessLevel.PRIVATE)  //추가 디폴트 생성자를 만든다. 이놈이 필요한이유는 JPA 덕분 
@JsonIgnoreProperties(ignoreUnknown = true)
public class Manager {

	@Id @GeneratedValue
	private Long id;
	private String name;
	
	public Manager(String name) {
		this.setName(name);
	}

	/**
	 * To break the recursive, bi-directional interface, don't serialize {@literal employees}.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "manager")
	private List<Employee> employees = new ArrayList<>();

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(this.id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
