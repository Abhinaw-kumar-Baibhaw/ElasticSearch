package com.example.TaskElasticSearch.controller;

import com.example.TaskElasticSearch.model.Task;
import com.example.TaskElasticSearch.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private Task mockTask;

    @BeforeEach
    void setUp() {
        mockTask = new Task("TestCity", "TestAddress", "TestType");
    }


    @Test
    void testFindByCity() throws Exception {
        Task mockTask = new Task();
        mockTask.setCity("TestCity");
        mockTask.setAddress("TestAddress");
        mockTask.setType("TestType");
        when(taskService.findTasks("TestCity", null, null)).thenReturn(Collections.singletonList(mockTask));
        mockMvc.perform(post("/apis/byCity")
                        .contentType("application/json")
                        .content("{\"city\":\"TestCity\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city").value("TestCity"))
                .andExpect(jsonPath("$[0].address").value("TestAddress"))
                .andExpect(jsonPath("$[0].type").value("TestType"));
        verify(taskService, times(1)).findTasks("TestCity", null, null);
    }

}
