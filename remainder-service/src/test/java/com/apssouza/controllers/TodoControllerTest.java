package com.apssouza.controllers;

import com.apssouza.BasicApplication;
import java.nio.charset.Charset;
import java.util.Arrays;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import com.apssouza.entities.ToDo;
import com.apssouza.repositories.TodoRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.mock.http.MockHttpOutputMessage;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author apssouza
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = BasicApplication.class)
public class TodoControllerTest {

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<ToDo> todoList = new ArrayList<>();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.todoRepository.deleteAllInBatch();
        ToDo toDo1 = new ToDo("caption 1", "description 1", 5);
        ToDo toDo2 = new ToDo("caption 2", "description 2", 4);
        this.todoList.add(todoRepository.save(toDo1));
        this.todoList.add(todoRepository.save(toDo2));
        List<ToDo> findAll = todoRepository.findAll();
        findAll.stream().forEach(t -> System.out.println(t.getId()));
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o,
                MediaType.APPLICATION_JSON,
                mockHttpOutputMessage
        );
        return mockHttpOutputMessage.getBodyAsString();
    }

    @Test
    public void userNotFound() throws Exception {
        mockMvc.perform(get("/todos/55")
                .content(this.json(new ToDo()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

}
