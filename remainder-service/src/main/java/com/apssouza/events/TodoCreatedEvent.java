package com.apssouza.events;

import com.apssouza.annotations.ChangeEvent;
import com.apssouza.entities.ToDo;

/**
 * To Do created event
 *
 * @author apssouza
 */
@ChangeEvent(ChangeEvent.Type.CREATION)
public class TodoCreatedEvent implements TodoChangedEvent {

    private ToDo todo;

    public TodoCreatedEvent(ToDo todo) {
        this.todo = todo;
    }

    public TodoCreatedEvent() {
    }

    public ToDo getTodo() {
        return todo;
    }

    public void setTodo(ToDo todo) {
        this.todo = todo;
    }

    @Override
    public int getPriority() {
        return this.todo.getPriority();
    }

    @Override
    public String toString() {
        return "TodoChangeEvent{" + "todo=" + todo.getDescription() + '}';
    }

}
