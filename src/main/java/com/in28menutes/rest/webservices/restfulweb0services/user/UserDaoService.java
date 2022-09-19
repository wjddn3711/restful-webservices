package com.in28menutes.rest.webservices.restfulweb0services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component // 스프링이 관리할 수 있도록 빈 등록
public class UserDaoService {
    // JPA/Hibernate > Database
    // UserDAOService > Static List

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount,"Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount,"Jim", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll(){
        return users;
    }
//
    public User save(User user){
        user.setId(++usersCount);
        users.add(user);
        return user;
    }
    public User findOne(int id){
        // if value is present it returns value, or else throw
        return users.stream().filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void delete(int id){
        users.removeIf(user -> user.getId() == id);
    }
}
