package com.apssouza.commands;

import java.util.UUID;

/**
 *
 * @author apssouza
 */
public class UserDeleteCommand {

    private long id;
    
    public UserDeleteCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    
}
