package co.com.todoList.crud.service;

import co.com.todoList.crud.dto.ResponseDTO;
import co.com.todoList.crud.entity.Task;
import co.com.todoList.crud.exception.TodoListExc;
import co.com.todoList.crud.repository.TaskRepository;
import co.com.todoList.crud.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getListTasks() throws TodoListExc {
        try {
            return taskRepository.findAll();
        } catch (Exception e) {
            throw new TodoListExc(e.getMessage());
        }
    }

    @Override
    public ResponseDTO saveUpdateTask(Task task) throws TodoListExc {
        try {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCodigoRespuesta(HttpStatus.CREATED.value());
            if (task.getId() != null) {
                Task taskFound = getTaskById(task.getId());
                if (taskFound == null) {
                    throw new TodoListExc("No se encontro la tarea para ser eliminada!");
                }
                taskFound.setPriority(task.getPriority());
                taskFound.setNameTark(task.getNameTark());
                taskFound.setDescription(task.getDescription());
                taskFound.setLastModifiedDate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
                responseDTO.setMensaje("Tarea actualizada correctamente");
                responseDTO.setData(taskRepository.save(taskFound));
                return responseDTO;
            }
            task.setStatus(Constantes.CREADA);
            task.setCreated(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
            responseDTO.setData(taskRepository.save(task));

            responseDTO.setMensaje("Tarea guardada correctamente.");

            return responseDTO;
        } catch (Exception e) {
            throw new TodoListExc(e.getMessage());
        }
    }

    @Override
    public ResponseDTO deleteTask(Integer idTask) throws TodoListExc {
        try {
            Task taskFound = getTaskById(idTask);
            if (taskFound == null)
                throw new TodoListExc("No se encontro la tarea para ser eliminada!");
            taskRepository.deleteById(idTask);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMensaje("Tarea eliminada correctamente.");
            responseDTO.setCodigoRespuesta(HttpStatus.OK.value());
            responseDTO.setData(taskRepository.findAll());
            return responseDTO;
        } catch (Exception e) {
            throw new TodoListExc(e.getMessage());
        }
    }

    @Override
    public ResponseDTO changeStatus(Integer idTask, String status) throws TodoListExc {
        try {
            ResponseDTO responseDTO = new ResponseDTO();
            Task taskFound = getTaskById(idTask);
            if (taskFound == null) {
                throw new TodoListExc("No se encontro la tarea para ser modificada!");
            }
            taskFound.setStatus(status);
            responseDTO.setData(taskRepository.save(taskFound));

            responseDTO.setMensaje("Cambio el estado de la tarea con el nombre : " + taskFound.getNameTark());
            responseDTO.setCodigoRespuesta(HttpStatus.CREATED.value());

            return responseDTO;
        } catch (Exception e) {
            throw new TodoListExc(e.getMessage());
        }
    }

    @Override
    public ResponseDTO changePriority(Integer idTask, String priority) throws TodoListExc {
        try {
            ResponseDTO responseDTO = new ResponseDTO();
            Task taskFound = getTaskById(idTask);
            if (taskFound == null) {
                throw new TodoListExc("No se encontro la tarea para ser modificada!");
            }
            taskFound.setPriority(priority);
            responseDTO.setData(taskRepository.save(taskFound));

            responseDTO.setMensaje("Cambio el estado de la tarea con el nombre : " + taskFound.getNameTark());
            responseDTO.setCodigoRespuesta(HttpStatus.CREATED.value());

            return responseDTO;
        } catch (Exception e) {
            throw new TodoListExc(e.getMessage());
        }
    }

    private Task getTaskById(Integer idTask) throws TodoListExc {
        try {
            Task task = taskRepository.findById(idTask).orElse(null);
            return task;
        } catch (Exception e) {
            throw new TodoListExc(e.getMessage());
        }
    }
}
