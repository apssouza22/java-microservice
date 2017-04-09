package com.apssouza.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apssouza
 */
@Entity
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Email
    @Column(name="email", nullable=false, unique=true)
    private String email;

    @NotEmpty
    private String password;

    @Version
    private long version;
    
    public Account() {
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    

}
