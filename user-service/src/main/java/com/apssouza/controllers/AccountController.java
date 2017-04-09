package com.apssouza.controllers;

import com.apssouza.entities.Account;
import com.apssouza.exceptions.DataNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.apssouza.services.AccountService;
import java.security.Principal;

/**
 *
 * @author apssouza
 */
@RequestMapping("/accounts")
@RestController
public class AccountController {

    @Autowired
    AccountService userService;

    @GetMapping
    public List<Account> all() {
        return this.userService.all();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Account account) {
        Account saved = this.userService.save(account);
        Long id = saved.getId();
        if (id != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(
            @PathVariable long id,
            @RequestBody @Valid Account user
    ) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable long id) {
        Optional<Account> findById = userService.findById(id);
        return findById.map(todo -> {
            return ResponseEntity.ok(todo);
        }).orElseThrow(() -> new DataNotFoundException("user not found"));
    }
    
    @GetMapping("me")
    public ResponseEntity<?> find(Principal principal) {
        Optional<Account> findById = userService.findByEmail(principal.getName());
        return findById.map(account -> {
            return ResponseEntity.ok(account);
        }).orElseThrow(() -> new DataNotFoundException("user not found"));
    }

}
