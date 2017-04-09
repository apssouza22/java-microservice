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
 *
 * @author apssouza
 */
@Entity
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private long authId;

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

    public Account(String name, String email, long authId) {
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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
