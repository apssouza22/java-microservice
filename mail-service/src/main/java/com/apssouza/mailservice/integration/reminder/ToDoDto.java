package com.apssouza.mailservice.integration.reminder;

import java.util.Date;

/**
 * To Do entity
 *
 * @author apssouza
 */
public class ToDoDto implements Cloneable {

    public enum TodoStatus {
        DONE, PENDING
    }

    private long id;

    private String caption;

    private String userEmail;

    private String description;

    private Date createdat;

    private int priority;

    private TodoStatus status = TodoStatus.PENDING;

    private long version;

    public ToDoDto() {
    }

    public ToDoDto(String email, String caption, String description, int priority) {
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

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public TodoStatus getStatus() {
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
