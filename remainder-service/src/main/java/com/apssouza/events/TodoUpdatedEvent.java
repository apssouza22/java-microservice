package com.apssouza.events;

import  com.apssouza.entities.ToDo;

/**
 *
 * @author apssouza
 */
public class TodoUpdatedEvent implements TodoChangedEvent{

    private ToDo todo;

    public TodoUpdatedEvent(ToDo todo) {
        this.todo = todo;
    }

    public TodoUpdatedEvent() {
    }

    public ToDo getTodo() {
        return todo;
    }

    public void setTodo(ToDo todo) {
        this.todo = todo;
    }
    
    public int getPriority(){
        return this.todo.getPriority();
    }

    @Override
    public String toString() {
        return "TodoChangeEvent{" + "todo=" + todo.getDescription() + '}';
    }


}
