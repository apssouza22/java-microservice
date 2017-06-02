package com.apssouza.eventsourcing.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author apssouza
 */
@Entity
public class Email {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private long authId;

    @NotNull
    @Size(min = 2, max = 256)
    private String name;

    @org.hibernate.validator.constraints.Email
    @NotNull
    @Column(unique = true)
    private String email;

    @Version
    private long version;

    public Email() {
    }

    public Email(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Email(long id, String name, String email) {
        this(name, email);
        this.id = id;
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

    @Override
    public String toString() {
        return "Email{" + "id=" + id + ", authId=" + authId + ", name=" + name + ", email=" + email + ", version=" + version + '}';
    }
    
}
