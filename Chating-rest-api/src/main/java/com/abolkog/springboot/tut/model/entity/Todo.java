package com.abolkog.springboot.tut.model.entity;

import javax.persistence.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//@Document
@Entity
@Table(name="Todo")
public class Todo implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String userId;

    @NotNull(message = "Title is required")
    @Size(min = 3, message = "Title must be at least 3 characters long")
    private String title;

    @NotNull(message = "Description is required")
    private String description;

    private java.sql.Timestamp timestamp;

    public Todo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timestamp = new java.sql.Timestamp(System.currentTimeMillis());
    }

    public Todo() {
        this.timestamp = new java.sql.Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.sql.Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
