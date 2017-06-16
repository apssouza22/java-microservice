package com.apssouza.services;

import com.apssouza.entities.Account;
import com.apssouza.exceptions.DataNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Account service interface
 *
 * @author apssouza
 */
public interface AccountService {

    Optional<Account> findById(long id);

    List<Account> all();

    Account save(Account user);

    Account update(Long id, Account user) throws DataNotFoundException;

    Optional<Account> findByEmail(String name);

    void delete(Account a);
}
