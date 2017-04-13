package com.apssouza.repositories;


import com.apssouza.entities.ToDo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author apssouza
 */
@Repository
public interface TodoRepository  extends JpaRepository<ToDo, Long>  {
    
     boolean deleteById(Long id);   
     
     List<ToDo> findByUserEmail(String email);
}
