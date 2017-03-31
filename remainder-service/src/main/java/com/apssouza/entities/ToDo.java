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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @NotNull
    @Size(min = 2, max = 256)
    private String caption;
    private String description;
    private int priority;

    @OneToMany(
            cascade = CascadeType.ALL, 
            mappedBy = "atachment", 
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Attachment> files;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "todo_category",
            joinColumns = {
                @JoinColumn(
                        name = "todo_id",
                        referencedColumnName = "id"
                ),
                @JoinColumn(
                        name = "category_id",
                        referencedColumnName = "id"
                )
            }
    )
    private Category category;

    private boolean done;

    @Version
    private long version;

    public ToDo(String caption, String description, int priority) {
        this.caption = caption;
        this.description = description;
        this.priority = priority;
    }

    public ToDo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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

    public Set<Attachment> getFiles() {
        return new HashSet<>(files);
    }

    public void setFiles(Set<Attachment> files) {
        this.files = new HashSet<>(files);
    }

    public Category getCategory() {
        return Category.newInstance(category);
    }

    public void setCategory(Category category) {
        this.category = Category.newInstance(category);
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public boolean isValid() {
        if (this.priority <= 10) {
            return true;
        }
        return (this.description != null && !this.description.isEmpty());
    }

    @Override
    public ToDo clone() throws CloneNotSupportedException {
        ToDo clone = (ToDo) super.clone();
        clone.setCategory(category);
        clone.setFiles(files);
        return clone;
    }

    @Override
    public String toString() {
        return "ToDo{" + "id=" + id + ", caption=" + caption + ", description=" + description + ", priority=" + priority + ", done=" + done + ", version=" + version + '}';
    }

}
