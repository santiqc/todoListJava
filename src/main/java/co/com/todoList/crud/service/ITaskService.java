package co.com.todoList.crud.service;

import co.com.todoList.crud.dto.ResponseDTO;
import co.com.todoList.crud.entity.Task;
import co.com.todoList.crud.exception.TodoListExc;

import java.util.List;

public interface ITaskService {

    List<Task> getListTasks() throws TodoListExc;

    ResponseDTO saveUpdateTask(Task task) throws TodoListExc;

    ResponseDTO deleteTask(Integer idTask) throws TodoListExc;

    ResponseDTO changeStatus(Integer idTask, String status) throws TodoListExc;
    ResponseDTO changePriority(Integer idTask, String priority) throws TodoListExc;


}
