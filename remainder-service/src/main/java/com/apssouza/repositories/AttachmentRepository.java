package com.apssouza.repositories;


import com.apssouza.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author apssouza
 */
@Repository
public interface AttachmentRepository  extends JpaRepository<Attachment, Long>  {
    
}
