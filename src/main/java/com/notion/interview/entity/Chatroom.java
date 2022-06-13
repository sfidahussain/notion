package com.notion.interview.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
/**
 *      * Chatroom
 *      *  list of messages: value: ["messsage1", "message2"]
 *      *  list of users: value: ["1", "2"]
 */
public class Chatroom {
    private List<User> users = new ArrayList<>();
    private String name;
    private List<Message> messages = new ArrayList<>();


    public List<User> addUser(User user) {
        users.add(user);
        return users;
    }
}
