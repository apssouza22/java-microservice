/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.pojo;

import com.apssouza.entities.ToDo;

/**
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
