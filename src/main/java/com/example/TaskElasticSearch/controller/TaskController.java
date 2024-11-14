package com.example.TaskElasticSearch.controller;


import com.example.TaskElasticSearch.model.Task;
import com.example.TaskElasticSearch.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("apis")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/byCity")
    public List<Task> findByCity(@RequestBody Map<String, String> request) {
        String city = request.get("city");
        String address = request.get("address");
        String type = request.get("type");
        List<Task> result = taskService.findTasks(city, address, type);
        return result;
    }

    @PostMapping("/insert")
    public Task insertProduct(@RequestBody Task product){
        return taskService.insertProduct(product);
    }


//
//    @GetMapping("/findAll")
//    public Iterable<Task> findAll(){
//        return  taskService.getData();
//    }
}
