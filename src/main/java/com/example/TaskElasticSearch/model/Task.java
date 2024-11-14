package com.example.TaskElasticSearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@NoArgsConstructor
@Document(indexName = "tasks")
public class Task {

    private int id;

    private String name;

    private String city;

    private String address;

    private String type;

    public Task(int id, String name, String city, String address, String type) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.type = type;
    }

    public Task(String testCity, String testAddress, String testType) {
    }
}
