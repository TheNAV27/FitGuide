package org.example.fitguide.service;

import org.example.fitguide.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<User>();

        User user = new User(1,"Vy","vilibarca79@gmail.com");
        User user2 = new User(2,"Hans",  "hans@mail.com");
        User user3 = new User(3,"Lars",  "lars@mail.com");
        User user4 = new User(4,"Ben",  "ben@mail.com");
        User user5 = new User(5,"Elise",  "elisemariebiswas@gmail.com");

        userList.addAll(Arrays.asList(user,user2,user3,user4,user5));
    }
    public List<User> getUserList() {
        return userList;
    }
    public Optional<User> getUser(Integer id) {
        Optional optional = Optional.empty();
        for(User user : userList) {
            if(id == user.getId()) {
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}
