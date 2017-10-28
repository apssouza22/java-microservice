package com.apssouza.entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * To Do's Attachment entity
 *
 * @author apssouza
 */
@Entity
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    @ManyToOne
    private ToDo todo;

    public Attachment() {
    }
    

    public Attachment(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ToDo getTodo() {
        return todo;
    }

    public void setTodo(ToDo todo) {
        this.todo = todo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Attachment other = (Attachment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
