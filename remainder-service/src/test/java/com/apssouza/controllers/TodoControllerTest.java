package com.apssouza.controllers;

import com.apssouza.BasicApplication;
import com.apssouza.entities.Attachment;
import com.apssouza.entities.Category;
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
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author apssouza
 */
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@SpringBootTest(classes = BasicApplication.class)
public class TodoControllerTest {
/*
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
        ToDo toDo1 = new ToDo("apssouza22@gmail.com","caption 1", "description 1", 5);
        ToDo toDo2 = new ToDo("alex@gmail.com","caption 2", "description 2", 4);

        Attachment attach1 = new Attachment("planilha", "xls");
        Attachment attach2 = new Attachment("phones", "xls");

        Category category = new Category("work");

        Set<Attachment> files = new HashSet<>();
        files.add(attach1);
        files.add(attach2);
        ArrayList categories = new ArrayList(Arrays.asList(
                new Category[]{category})
        );
        toDo1.setAttachments(files);
        toDo1.setCategories(categories);

        this.todoList.add(todoRepository.save(toDo1));
        this.todoList.add(todoRepository.save(toDo2));
        List<ToDo> findAll = todoRepository.findAll();
        findAll.stream().forEach(t -> {
            System.out.println(t.getId() + " ====>>> ");
            t.getCategories().stream().forEach(a -> System.out.println(a.getName()));
        });
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
    public void todoNotFound() throws Exception {
        mockMvc.perform(get("/todos/55")
                .content(this.json(new ToDo()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void readSingleTodo() throws Exception {
        mockMvc.perform(get("/todos/" + this.todoList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.todoList.get(0).getId().intValue())))
                .andExpect(jsonPath("$.description", is("description 1")));
    }

    @Test
    public void readTodos() throws Exception {
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(this.todoList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].caption", is(this.todoList.get(0).getCaption())))
                .andExpect(jsonPath("$[0].description", is(this.todoList.get(0).getDescription())))
                .andExpect(jsonPath("$[1].id", is(this.todoList.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].description", is(this.todoList.get(1).getDescription())));
    }

    @Test
    public void createTodo() throws Exception {
        String todoJson = json(new ToDo("apssouza@gmail.com","caption 1", "description 1", 6));

        ResultActions perform = this.mockMvc.perform(post("/todos")
                .contentType(contentType)
                .content(todoJson));
        perform.andExpect(status().isCreated());
    }
*/

}
