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
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Domain object representing a company employee. Project Lombok keeps actual code at a minimum.
 *
 * {@code @Data} - Generates getters, setters, toString, hash, and equals functions
 * {@code @Entity} - JPA annotation to flag this class for DB persistence
 * {@code @NoArgsConstructor} - Create a constructor with no args to support JPA
 * {@code @AllArgsConstructor} - Create a constructor with all args to support testing
 *
 * {@code @JsonIgnoreProperties(ignoreUnknow=true)}
 * When converting JSON to Java, ignore any unrecognized attributes. This is critical for REST because it
 * encourages adding new fields in later versions that won't break. It also allows things like _links to be
 * ignore as well, meaning HAL documents can be fetched and later posted to the server without adjustment.
 *
 *
 * @author Greg Turnquist
 */
@Data
@Entity
@AllArgsConstructor //추가 모든 필드가 있는 생성자를 만든다.
@NoArgsConstructor(access = AccessLevel.PRIVATE)  //추가 디폴트 생성자를 만든다. 이놈이 필요한이유는 JPA 덕분 
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

	@Id @GeneratedValue
	private Long id;
	private String name;	
	private String role;

	@OneToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private Manager manager;
	
	public Employee(String name, String role, Manager manager) {

		this.setName(name);
		this.setRole(role);
		this.manager = manager;
	}
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Long getId() {
		return this.id;
	}
	
	public Optional<Long> getEmployeeId() {
		return Optional.ofNullable(this.id);
	}
	

	/**
	 * This method will create another piece of data in the REST resource representation. These types
	 * of methods are key in supporting backward compatibility.
	 *
	 * By NOT removing old fields, and instead replacing them with methods like this, an API can evolve
	 * without breaking old clients.
	 *
	 * Because of {@code @JsonIgnoreProperties} settings above, this attribute will be ignore if sent back
	 * to the server, allowing API evolution.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
