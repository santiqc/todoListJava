package co.com.todoList.crud.repository;

import co.com.todoList.crud.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
