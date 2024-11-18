package com.example.TaskElasticSearch.exception;

import static javax.swing.UIManager.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.TaskElasticSearch.enums.ErrorCode;
import com.example.TaskElasticSearch.exceptions.ResourceNotFoundException;
import com.example.TaskElasticSearch.model.Task;
import com.example.TaskElasticSearch.repo.TaskRepo;
import com.example.TaskElasticSearch.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class TaskExceptionTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private TaskService taskService;

    @Mock
    private WebClient webClient;


    @Mock
    private WebClient.RequestBodySpec requestBodySpec;


    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testMethodThrowsNullPointerException() {
        String city = "somecity";
        String address = "some address";
        String type = "some type";
       assertThrows(NullPointerException.class, () -> {
            taskService.findTasks(city, address, type);
        });
    }
    @Test
    public void testMethodThrowsNullPointerException2() {
        String city = "somecity";
        String address = "some address";
        assertThrows(NullPointerException.class, () -> {
            taskService.findTasks(city, address, null);
        });
    }

    @Test
    public void testMethodThrowsNullPointerException3() {
        String city = "somecity";
        assertThrows(NullPointerException.class, () -> {
            taskService.findTasks(city, null, null);
        });
    }
}
