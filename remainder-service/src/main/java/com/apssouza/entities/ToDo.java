package com.apssouza.entities;

import com.apssouza.monitors.ToDoPersistenceMonitor;
import com.apssouza.validation.ValidEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import com.apssouza.validation.CheckIsValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author apssouza
 */
@Entity
@CheckIsValid
@EntityListeners(ToDoPersistenceMonitor.class)
public class ToDo implements ValidEntity, Cloneable {
    
    public enum TodoStatus { DONE, PENDING }

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 2, max = 256)
    private String caption;
    
    @Column(nullable = false, unique = true)
    private String userEmail;
            
    private String description;
    
    private int priority;

    @OneToMany(
            cascade = CascadeType.ALL, 
            mappedBy = "todo", 
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Attachment> files = new HashSet<>();

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "todo_category",
            joinColumns = {
                @JoinColumn(
                        name = "todo_id",
                        referencedColumnName = "id"
                )
            },
            inverseJoinColumns = {
                @JoinColumn(
                        name = "category_id",
                        referencedColumnName = "id"
                )
            }
    )
    private List<Category> categories = new ArrayList<>();;

    private TodoStatus status = TodoStatus.PENDING;

    @Version
    private long version;

    public ToDo() {
    }
    
    public ToDo(String email, String caption, String description, int priority) {
        this.caption = caption;
        this.description = description;
        this.priority = priority;
        this.userEmail = email;
    }

    public ToDo(ToDo todo, List<Category> categories, Set<Attachment> files, TodoStatus status) {
        this(todo.getUserEmail(), todo.getCaption(), todo.getDescription(), todo.getPriority());
        this.categories = Collections.unmodifiableList(categories);
        this.files = Collections.unmodifiableSet(files);
        this.status = status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    
    public void setCategories(List<Category> categories){
        this.categories = Collections.unmodifiableList(categories);
    }
    
    public void setAttachments(Set<Attachment> files){
        this.files = Collections.unmodifiableSet(files);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setFiles(Set<Attachment> files) {
        this.files = files;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
    
    public void setStatus(TodoStatus status){
        this.status = status;
    }

    public TodoStatus getStatus(){
        return status;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
    
    @JsonIgnore
    public Set<Attachment> getAttachments() {
        return new HashSet<>(files);
    }
    
    @JsonIgnore
    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean isValid() {
        if (this.priority <= 10) {
            return true;
        }
        return (this.description != null && !this.description.isEmpty());
    }

    @Override
    public String toString() {
        return "ToDo{" + "id=" + id + ", caption=" + caption + ", description=" + description + ", priority=" + priority + ", done=" + status + ", version=" + version + '}';
    }

}
