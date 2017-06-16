package com.apssouza.clients;

import com.apssouza.pojos.Todo;
import com.apssouza.pojos.User;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Declarative To do REST client
 *
 * @author apssouza
 */
@FeignClient("todo")
public interface TodoClient {

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public List<User> getAll();

    @RequestMapping(value = "/todos/search", method = RequestMethod.GET)
    public List<Todo> getTodoByUserEmaill(@RequestParam("email") String email);

    @RequestMapping(
            value = "/todos",
            method = RequestMethod.POST
    )
    Todo createTodo(Todo todo);
}
