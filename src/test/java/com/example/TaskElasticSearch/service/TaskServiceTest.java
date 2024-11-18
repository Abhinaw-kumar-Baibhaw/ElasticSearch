package com.example.TaskElasticSearch.service;

import com.example.TaskElasticSearch.model.Task;
import com.example.TaskElasticSearch.repo.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        WebClient.RequestBodyUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpec = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
        when(requestBodySpec.bodyValue(any(Object.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Task.class)).thenReturn(Mono.just(new Task("TestCity", "TestAddress", "TestType")));
    }


    @Test
    void testFindTasks_ThirdPartyCall1() {
        when(taskRepo.findByCityAndAddress(anyString(), anyString())).thenReturn(Collections.emptyList());
        List<Task> tasks = taskService.findTasks("TestCity", "TestAddress", null);
        assertEquals(1, tasks.size());
        assertNotNull(tasks);
        verify(taskRepo, times(1)).findByCityAndAddress("TestCity", "TestAddress");
        verify(webClientBuilder, times(1)).build();
    }


    @Test
    void testFindTasks_ThirdPartyCall2() {
        when(taskRepo.findByCityAndType(anyString(), anyString())).thenReturn(Collections.emptyList());
        List<Task> tasks = taskService.findTasks("TestCity", null, "permanent");
        assertEquals(1, tasks.size());
        assertNotNull(tasks);
        verify(taskRepo, times(1)).findByCityAndType("TestCity", "permanent");
        verify(webClientBuilder, times(1)).build();
    }


    @Test
    void testFindTasks_ThirdPartyCall3() {
        when(taskRepo.findAllByCity(anyString())).thenReturn(Collections.emptyList());
        List<Task> tasks = taskService.findTasks("TestCity", null, null);
        assertEquals(1, tasks.size());
        assertNotNull(tasks);
        verify(taskRepo, times(1)).findAllByCity("TestCity");
        verify(webClientBuilder, times(1)).build();
    }


    @Test
    public void testFindTasks_WithCityAndAddress() {
        String city = "New York";
        String address = "123 Main St";
        Task task = new Task();
        task.setId(1);
        task.setName("Sample Task");
        when(taskRepo.findByCityAndAddress(city, address)).thenReturn(List.of(task));
        List<Task> result = taskService.findTasks(city, address, null);
        assertEquals(1, result.size());
        assertEquals(task, result.get(0));
        verify(taskRepo, times(1)).findByCityAndAddress(city, address);
    }


    @Test
    public void testFindTasks_WithCityAndType() {
        String city = "New York";
        String type = "Type1";
        Task task = new Task();
        task.setId(2);
        task.setName("Another Task");
        when(taskRepo.findByCityAndType(city, type)).thenReturn(List.of(task));
        List<Task> result = taskService.findTasks(city, null, type);
        assertEquals(1, result.size());
        assertEquals(task, result.get(0));
        verify(taskRepo, times(1)).findByCityAndType(city, type);
    }


    @Test
    public void testFindTasks_WithCity() {
        String city = "New York";
        Task task = new Task();
        task.setId(3);
        task.setName("Task Only By City");
        when(taskRepo.findAllByCity(city)).thenReturn(List.of(task));
        List<Task> result = taskService.findTasks(city, null, null);
        assertEquals(1, result.size());
        assertEquals(task, result.get(0));
        verify(taskRepo, times(1)).findAllByCity(city);
    }

}
