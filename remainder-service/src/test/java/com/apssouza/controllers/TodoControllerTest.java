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
import com.apssouza.repositories.CategoryRepository;
import com.apssouza.repositories.TodoRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Locale;
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
    private CategoryRepository categoryRepository;

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

        Attachment attach1 = new Attachment("planilha", "xls");
        Attachment attach2 = new Attachment("phones", "xls");

        Category category = new Category("work");

        Set<Attachment> files = new HashSet<>();
        files.add(attach1);
        files.add(attach2);
        ArrayList categories = new ArrayList(Arrays.asList(
                new Category[]{category})
        );
        ToDo newTodo1 = toDo1.addAttachments(files);
        ToDo toDo3 = newTodo1.addCategory(categories);
        
        this.todoList.add(todoRepository.save(toDo3));
        this.todoList.add(todoRepository.save(toDo2));
        List<Category> findAll = categoryRepository.findAll();
        findAll.stream().forEach(t -> {
            System.out.println(t.getName() + " ==== " );
             //t.getAttachments().stream().forEach( a -> System.out.println(a.getName()));
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
    public void userNotFound() throws Exception {
        mockMvc.perform(get("/todos/55")
                .content(this.json(new ToDo()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

}
