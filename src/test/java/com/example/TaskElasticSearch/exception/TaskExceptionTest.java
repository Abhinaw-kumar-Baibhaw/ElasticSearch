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
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestBodySpec requestBodySpec;


    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Autowired
    private MockMvc mockMvc;

     @Test
    public void testMethodThrowsException() {
        String city = "some city";
        String address = "some address";
        String type = "some type";
        assertThrows(NullPointerException.class, ()->{
             taskService.findTasks(null,null,null);
         });
    }

    @Test
    public void testMethodThrowsException1() {
        String city = "some city";
        String address = "some address";
        String type = "some type";
        assertThrows(NullPointerException.class, ()->{
            taskService.findTasks(null,address,type);
        });
    }

    @Test
    public void testMethodThrowsException2() {
        String city = "some city";
        String address = "some address";
        String type = "some type";
        assertThrows(NullPointerException.class, ()->{
            taskService.findTasks(null,null,type);
        });
    }

    @Test
    public void testMethodThrowsResourceNotFoundException() {
        String city = null;
        String address = "some address";
        String type = "some type";
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            taskService.findTasks(city, address, type);
        });
    }

    @Test
    public void testControllerThrowsResourceNotFoundException() throws Exception {
        mockMvc.perform(post("/apis/byCity"))
                .andExpect(status().isNotFound())
                .andExpect((ResultMatcher) content().string(ErrorCode.RESOURCE_NOT_FOUND.toString()));
    }

}
