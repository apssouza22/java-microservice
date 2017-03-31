package com.apssouza.entities;

import com.apssouza.monitors.ToDoPersistenceMonitor;
import com.apssouza.validation.ValidEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import com.apssouza.validation.CheckIsValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.Collation;
import javax.persistence.CascadeType;
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
@NamedQuery(name = ToDo.findAll, query = "SELECT t FROM ToDo t")
@CheckIsValid
@EntityListeners(ToDoPersistenceMonitor.class)
public class ToDo implements ValidEntity, Cloneable {

    @Id
    @GeneratedValue
    private long id;

    static final String PREFIX = "ToDo.";
    public static final String findAll = PREFIX + "findAll";
    
    public enum TodoStatus { DONE, PENDING }

    @NotNull
    @Size(min = 2, max = 256)
    private String caption;
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
    
    public ToDo(String caption, String description, int priority) {
        this.caption = caption;
        this.description = description;
        this.priority = priority;
    }

    public ToDo(ToDo todo, List<Category> categories, Set<Attachment> files, TodoStatus status) {
        this(todo.getCaption(), todo.getDescription(), todo.getPriority());
        this.categories = Collections.unmodifiableList(categories);
        this.files = Collections.unmodifiableSet(files);
        this.status = status;
    }
    
    public ToDo addCategory(List<Category> categories){
        return new ToDo(this, categories, files, status);
    }
    
    public ToDo addAttachments(Set<Attachment> files){
        return new ToDo(this, categories, files, status);
    }
    
    
    public ToDo updateStatus(TodoStatus status){
        return new ToDo(this, this.categories, this.files, this.status);
    }

    public TodoStatus getStatus(){
        return status;
    }
    
    public long getId() {
        return id;
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
