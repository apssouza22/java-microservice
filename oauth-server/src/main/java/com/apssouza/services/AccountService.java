package com.apssouza.services;

import com.apssouza.entities.Account;
import java.util.Optional;

/**
 *
 * @author apssouza
 */
public interface AccountService {

    Optional<Account> findByEmail(String name);

}
