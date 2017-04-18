package com.apssouza.commands;

import java.util.UUID;

/**
 *
 * @author apssouza
 */
public class UserUpdateCreateCommand {

    private UUID uuid;
    
    private long id;

    private long authId;

    private String name;

    private String email;

    public UserUpdateCreateCommand(long id, long authId, String name, String email) {
        this(authId, name, email);
        this.id = id;
    }
    
    public UserUpdateCreateCommand(long authId, String name, String email) {
        this.authId = authId;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public long getAuthId() {
        return authId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UUID getUuid() {
        return uuid;
    }
    
    
}
