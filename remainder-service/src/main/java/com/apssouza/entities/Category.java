package com.apssouza.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * To Do's Category entity
 *
 * @author apssouza
 */
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(
            mappedBy = "categories",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<ToDo> todos = new ArrayList<>();

    ;

    public Category(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    /**
     * Defensive copying Provide an attractive alternative to the rather
     * pathological clone method
     *
     * @param category
     * @return Category
     */
    public static Category newInstance(Category category) {
        return new Category(
                category.getId(),
                category.getName()
        );
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

    public List<ToDo> getTodos() {
        return todos;
    }

    public void setTodos(List<ToDo> todos) {
        this.todos = todos;
    }

}
