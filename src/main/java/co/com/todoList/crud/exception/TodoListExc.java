package co.com.todoList.crud.exception;

import lombok.Getter;

@Getter
public class TodoListExc extends RuntimeException {

    private final String mensaje;

    public TodoListExc(String message) {
        super(message);
        this.mensaje = message;
    }

    @Override
    public String toString() {
        return "TodoListExc{" +
                "mensaje='" + mensaje + '\'' +
                '}';
    }
}
