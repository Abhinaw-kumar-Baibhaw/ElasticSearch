package com.example.TaskElasticSearch.service;

import com.example.TaskElasticSearch.enums.ErrorCode;
import com.example.TaskElasticSearch.exceptions.NullPointerException;
import com.example.TaskElasticSearch.exceptions.ResourceNotFoundException;
import com.example.TaskElasticSearch.model.Task;
import com.example.TaskElasticSearch.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.HashMap;
import java.util.List;


@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String url = "http://localhost:8081/jpa/getByCriteria";

    public List<Task> findTasks(String city, String address, String type) throws ResourceNotFoundException {
        List<Task> result = null;
        if (city != null && address != null) {
            result = (List<Task>) taskRepo.findByCityAndAddress(city, address);
        } else if (city != null && type != null) {
            result = taskRepo.findByCityAndType(city, type);
        } else if (city != null) {
            result = taskRepo.findAllByCity(city);
        }
        else {
            if(city == null){
                throw new NullPointerException(ErrorCode.CITY_NOT_FOUND);
            }
            if(address == null){
                throw new NullPointerException(ErrorCode.ADDRESS_NOT_FOUND);
            }
            if(type == null){
                throw new NullPointerException(ErrorCode.TYPE_NOT_FOUND);
            }
//            result = (List<Task>) taskRepo.findAll();
        }
        if (result == null || result.isEmpty()) {
            HashMap<String , String > createRequestPayload = new HashMap<>();
            if(city != null) createRequestPayload.put("city",city);
            if(address != null) createRequestPayload.put("address",address);
            if(type != null) createRequestPayload.put("type", type);
            Task alternateDataArray = webClientBuilder.build()
                    .post()
                    .uri(url)
                    .bodyValue(createRequestPayload)
                    .retrieve()
                    .bodyToMono(Task.class)
                    .block();
            if (alternateDataArray != null ) {
                result = List.of(alternateDataArray);
            }
            else {
               throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND);
            }
        }
        return result;
    }

//    public Task insertProduct(Task product) throws Exception{
//        return taskRepo.save(product);
//    }

}
