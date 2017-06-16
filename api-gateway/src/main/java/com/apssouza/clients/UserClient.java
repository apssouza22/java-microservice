package com.apssouza.clients;

import com.apssouza.pojos.Todo;
import com.apssouza.pojos.User;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Declarative User REST client
 *
 * @author apssouza
 */
@FeignClient("user")
public interface UserClient {

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<User> getAll();

    @RequestMapping(value = "/accounts/search", method = RequestMethod.GET)
    public User getUserByEmail(@RequestParam("email") String email);

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    Todo createUser(User user);
}
