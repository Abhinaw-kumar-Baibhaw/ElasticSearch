package com.example.TaskElasticSearch.repo;

import com.example.TaskElasticSearch.model.Task;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepo extends ElasticsearchRepository<Task,Integer> {
//    List<Task> findAllByCity(String city);
//    Task findByCityAndAddress(String city, String address);
//    Task findByCityAndType(String city, String type);

    @Query("SELECT t FROM Task t WHERE t.city = :city")
    List<Task> findAllByCity(@Param("city") String city);

    @Query("SELECT t FROM Task t WHERE t.city = :city AND t.address = :address")
    Task findByCityAndAddress(@Param("city") String city, @Param("address") String address);

    @Query("SELECT t FROM Task t WHERE t.city = :city AND t.type = :type")
    List<Task> findByCityAndType(@Param("city") String city, @Param("type") String type);
}
