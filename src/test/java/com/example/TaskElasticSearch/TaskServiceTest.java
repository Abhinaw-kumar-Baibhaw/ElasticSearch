package com.example.TaskElasticSearch;

import com.example.TaskElasticSearch.model.Task;
import com.example.TaskElasticSearch.repo.TaskRepo;
import com.example.TaskElasticSearch.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.build()).thenReturn(webClient);
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
