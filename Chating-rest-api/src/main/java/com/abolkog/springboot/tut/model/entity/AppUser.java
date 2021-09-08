package com.abolkog.springboot.tut.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.sql.Date;

//@Document(collection = "users")
@Entity
@Table(name="app_user")
public class AppUser implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    @JsonIgnore
    private String password;

    private Date created;

    public AppUser() {
    }

    public AppUser(@NotEmpty String email, @NotEmpty String password, @NotEmpty String name) {
        
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    /**
     * inherited from UserDetails
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {//convert to email-property
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
