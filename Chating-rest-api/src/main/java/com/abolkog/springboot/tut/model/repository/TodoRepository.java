package com.abolkog.springboot.tut.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.abolkog.springboot.tut.model.entity.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer > {

    //Mongo will relate how to find the thing you need to find by the camel case and pass the value to parameter
    // example: findByDescriptionAndTimestampAndId(String desc, String timeStamp, String id)
    Todo findByTitle(String title);

    Todo findByTitleAndIdNot(String title, int id);

    List<Todo> findByUserId(String userId);

    Todo findByIdAndUserId(String userId, int id);

    boolean existsByUserIdAndId(String userId, int id);
}
