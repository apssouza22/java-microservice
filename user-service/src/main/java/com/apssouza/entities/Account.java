package com.apssouza.entities;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import org.hibernate.validator.constraints.Email;

/**
 * Account Entity
 *
 * @author apssouza
 */
@Entity
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 2, max = 256)
    private String name;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @Version
    private long version;

    public Account() {
    }

    public Account(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.password = pass;
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

    public String getPassword() {
        return password;
    }

    public void setAuthId(String pass) {
        this.password = pass;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
