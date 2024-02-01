package co.com.todoList.crud.controller;

import co.com.todoList.crud.dto.ResponseDTO;
import co.com.todoList.crud.entity.Task;
import co.com.todoList.crud.exception.TodoListExc;
import co.com.todoList.crud.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    private ITaskService iTaskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getListTask() throws TodoListExc {
        return new ResponseEntity<>(iTaskService.getListTasks(), HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<ResponseDTO> saveTask(@RequestBody Task task) throws TodoListExc {
        return new ResponseEntity<>(iTaskService.saveUpdateTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/task")
    public ResponseEntity<ResponseDTO> updateTask(@RequestBody Task task) throws TodoListExc {
        return new ResponseEntity<>(iTaskService.saveUpdateTask(task), HttpStatus.CREATED);
    }

    @DeleteMapping("/task/{idTask}")
    public ResponseEntity<ResponseDTO> deleteTask(@PathVariable Integer idTask) throws TodoListExc {
        return new ResponseEntity<>(iTaskService.deleteTask(idTask), HttpStatus.OK);
    }

    @PatchMapping("/task-status/{idTask}/{status}")
    public ResponseEntity<ResponseDTO> changeStatus(@PathVariable Integer idTask, @PathVariable String status) throws TodoListExc {
        return new ResponseEntity<>(iTaskService.changeStatus(idTask, status), HttpStatus.CREATED);
    }

    @PatchMapping("/task-priority/{idTask}/{priority}")
    public ResponseEntity<ResponseDTO> changePriority(@PathVariable Integer idTask, @PathVariable String priority) throws TodoListExc {
        return new ResponseEntity<>(iTaskService.changePriority(idTask, priority), HttpStatus.CREATED);
    }
}
