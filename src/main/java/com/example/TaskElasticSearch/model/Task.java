package com.example.TaskElasticSearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;


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
