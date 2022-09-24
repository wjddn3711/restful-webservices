package com.in28menutes.rest.webservices.restfulweb0services.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 10)
    private String description;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // default 로 fetch type 이 eager 임, 즉 post findAll 을 하게 되면 유저에 대한 정보도 가져옴
    // 이 프로젝트에서는 User에 대한 정보를 가져오지 않을 것이기에 LAZY 로 fetch 해온다. 즉, User 정보는 가져오지 않는다
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Post() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
}
