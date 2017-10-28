package com.apssouza.mailservice.integration.reminder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * To Do entity
 * @author apssouza
 */
public class ToDo implements Cloneable {
    
    public enum TodoStatus { DONE, PENDING }

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min = 2, max = 256)
    private String caption;
    
    @Column(nullable = false)
    private String userEmail;
            
    private String description;
    
    @Generated(GenerationTime.INSERT) //ALWAYS, UPDATE
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdat;
    
    private int priority;
    
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public long getVersion() {
        return version;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    @Override
    public String toString() {
        return "ToDo{" + "id=" + id + ", caption=" + caption + ", description=" + description + ", priority=" + priority + ", done=" + status + ", version=" + version + '}';
    }

}
