
package com.apssouza.clients;

import com.apssouza.pojos.User;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apssouza
 */
@FeignClient("user")
public interface UserClient {
    
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<User> all();
    
}
