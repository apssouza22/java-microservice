package com.apssouza.mailservice.controllers;

import com.apssouza.eventsourcing.entities.Email;
import com.apssouza.mailservice.repository.EmailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author apssouza
 */
@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    EmailRepository repository;

    @GetMapping
    public List<Email> list() {
        return repository.findAll();
    }

}
