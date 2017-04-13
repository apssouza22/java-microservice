/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apssouza.entities;

import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author apssouza
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {RepositoryConfiguration.class})
public class TodoTest {
   
    
    @Test
    public void validateTodoValid() {
        ToDo toDo = new ToDo("apssouza22@gmail.com","caption", "description 1", 6);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> constraintViolations = validator.validate(toDo);
        assertTrue(constraintViolations.isEmpty());       
    }
    
     @Test
    public void validateTodoInValid() {
        ToDo toDo = new ToDo("apssouza22@gmail.com","c", "description 1", 6);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> constraintViolations = validator.validate(toDo);

        if (!constraintViolations.isEmpty()) {
            Iterator itr = constraintViolations.iterator();
            while (itr.hasNext()) {
                ConstraintViolation next = (ConstraintViolation)itr.next();
                Assert.assertEquals("size must be between 2 and 256", next.getMessage());
            }
        }
    }
    
    
    
}
