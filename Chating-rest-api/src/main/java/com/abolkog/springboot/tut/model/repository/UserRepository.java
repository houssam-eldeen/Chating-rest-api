package com.abolkog.springboot.tut.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.abolkog.springboot.tut.model.entity.AppUser;



@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByEmail(String email);
}
