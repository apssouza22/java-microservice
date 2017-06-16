package com.apssouza.pojos;

/**
 * User Pojo
 *
 * @author apssouza
 */
public class User {

    private long id;

    private long authId;

    private String name;

    private String email;

    public User() {
    }

    public User(String name, String email, long authId) {
        this.name = name;
        this.email = email;
        this.authId = authId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAuthId() {
        return authId;
    }

    public void setAuthId(long authId) {
        this.authId = authId;
    }

}
