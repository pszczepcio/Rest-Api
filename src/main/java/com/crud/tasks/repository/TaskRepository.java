package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@SuppressWarnings("unchecked")
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    @SuppressWarnings("unchecked")
    Task save(Task task);

    Optional<Task> findById(Long id);

    @Override
    void delete(Task task);

    @Override
    long count();
}
