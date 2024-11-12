package com.example.TaskElasticSearch.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.lang.annotation.Documented;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "tasks")
public class Task {

    private int id;

    private String name;

    private String city;

    private String address;

    private String type;
}
