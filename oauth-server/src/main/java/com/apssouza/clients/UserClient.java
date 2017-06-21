
package com.apssouza.clients;

import com.apssouza.pojos.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apssouza
 */
@FeignClient("user")
@Component
public interface UserClient {
    
    @RequestMapping(value = "/accounts/search", method = RequestMethod.GET)
    public User getUserByEmail(@RequestParam("email") String email);
    
}
