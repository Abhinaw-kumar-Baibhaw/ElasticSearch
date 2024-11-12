package com.example.TaskElasticSearch.service;

import com.example.TaskElasticSearch.model.Task;
import com.example.TaskElasticSearch.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Task> ByCity(String city){
       List<Task> result = taskRepo.findAllByCity(city);
        return result;
    }
    public Task ByCityndAddress(String city,String address){
        Task result = taskRepo.findByCityAndAddress(city,address);
        return result;
    }

    public List<Task> ByCityndType(String city,String type){
        List<Task> result =taskRepo.findByCityAndType(city,type);
        return result;
    }

    public Task insertProduct(Task product){
        return taskRepo.save(product);
    }

    public Iterable<Task> getData(){
        return taskRepo.findAll();
    }
}
