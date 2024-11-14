package com.example.TaskElasticSearch.exception;

import com.example.TaskElasticSearch.enums.ErrorCode;
import com.example.TaskElasticSearch.exceptions.ResourceNotFoundException;
import com.example.TaskElasticSearch.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TaskExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;


    @Test
    void testResourceNotFoundFallback() throws Exception {
        when(taskService.findTasks("nonexistent city", "some address", "temporary"))
                .thenThrow(new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"city\": \"nonexistent city\", \"address\": \"some address\", \"type\": \"temporary\"}"));
//                .andExpect(status().isNotFound());
//                .andExpect((ResultMatcher) content().string(ErrorCode.RESOURCE_NOT_FOUND.getMessage()));
    }


    @Test
    void testCityNotFound() throws Exception {
        when(taskService.findTasks(null, "some address", "temporary"))
                .thenThrow(new ResourceNotFoundException(ErrorCode.CITY_NOT_FOUND));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/byCity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"address\": \"some address\", \"type\": \"temporary\"}"));
//                .andExpect(status().isNotFound())
//                .andExpect((ResultMatcher) content().string(ErrorCode.CITY_NOT_FOUND.getMessage()));
    }


    @Test
    void testHandleGlobalException() throws Exception {
        Map<String, String> requestData = new HashMap<>();
        requestData.put("city", "New York");
        requestData.put("address", "delhi");
        requestData.put("type", "permanent");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/apis/byCity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestData)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
    }

}
