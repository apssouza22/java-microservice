package com.apssouza.mailservice.repository;

import com.apssouza.eventsourcing.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author apssouza
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{
    
}
