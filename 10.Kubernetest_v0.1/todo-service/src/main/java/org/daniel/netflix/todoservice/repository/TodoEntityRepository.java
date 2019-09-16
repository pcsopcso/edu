package org.daniel.netflix.todoservice.repository;

import org.daniel.netflix.todoservice.model.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "todos")
public interface TodoEntityRepository extends JpaRepository<TodoEntity, Long> {

    @RestResource(path = "/user", rel = "todo")
    Page<TodoEntity> findByUser(@Param("user") String user, Pageable pageable);
}
