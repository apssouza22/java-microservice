package com.apssouza.pojos;

import com.apssouza.entities.ToDo;

/**
 * Socket message output.
 *
 * @author apssouza
 */
public class SocketOutputMessage {

    private final ToDo todo;
    private final String state;
    private final String time;

    public SocketOutputMessage(ToDo todo, String state, String time) {
        this.todo = todo;
        this.state = state;
        this.time = time;
    }

    public ToDo getTodo() {
        return todo;
    }

    public String getState() {
        return state;
    }

    public String getTime() {
        return time;
    }

}
