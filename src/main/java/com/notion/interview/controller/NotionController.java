package com.notion.interview.controller;

import com.notion.interview.entity.Chatroom;
import com.notion.interview.entity.Message;
import com.notion.interview.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotionController {
    /**
     *
     * User
     *  id: 1
     *  name: "Bob"
     * Message:
     *  content: "message1"
     *
     * One user can be included in many chatrooms (one-to many)
     * Multiple messages can be included in one chatroom (many-to-one)
     */
    private Chatroom c1;
    private Chatroom c2;
    private List<Chatroom> chatroomsList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();

    NotionController() {
        c1 = new Chatroom();
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("Michelle");
        users.add(user1);
        c1.setUsers(users);
        c1.setName("c1");

        userList.add(user1);

        c2 = new Chatroom();
        c2.setName("c2");

        chatroomsList.add(c1);
        chatroomsList.add(c2);
    }

    /**
     * A user can join a chat room
     * chatroom's list of users gets updated
     */
    @GetMapping("/join/{chatroomName}")
    public List<User> joinChat(@RequestBody User user, @PathVariable String chatroomName) {
        Chatroom joinedChat = searchChatrooms(chatroomName);
        userList.add(user);
        return joinedChat.addUser(user);
    }

    /**
     * After joining, a user can send a message to that chat room.
     */
    @PostMapping("/message/{userName}/{chatroomName}/{message}")
    public void sendMessage(@PathVariable String userName, @PathVariable String message, @PathVariable String chatroomName) {
        User user = findUser(userName);
        Message message1 = new Message();
        message1.setContent(message);
        message1.setUser(user);
        Chatroom joinedChat = searchChatrooms(chatroomName);
        List<Message> messages = joinedChat.getMessages();
        messages.add(message1);
        joinedChat.setMessages(messages);
    }

    /**
     * A user can retrieve messages from a chat room that they have joined.
     */
    @GetMapping("/messages/{chatroomName}")
    public List<Message> retrieveMessages(@PathVariable String chatroomName) {
        Chatroom joinedChat = searchChatrooms(chatroomName);
        return joinedChat.getMessages();
    }

    /**
     * Can leave a chatroom
     */
    @DeleteMapping("/leave/{userName}/{chatroomName}")
    public void leaveChatroom(@PathVariable String userName, @PathVariable String chatroomName) {
        User user = findUser(userName);
        Chatroom joinedChat = searchChatrooms(chatroomName);
        List<User> users = joinedChat.getUsers();
        users.remove(user);
        joinedChat.setUsers(users);
    }

    private User findUser(String userName) {
        for (User user: userList) {
            if(user.getName().equals(userName)) {
                return user;
            }
        }

        throw new RuntimeException("User not found.");
    }

    private Chatroom searchChatrooms(String chatroomName) {
        Chatroom joinedChat = new Chatroom();
        for (Chatroom chatroom: chatroomsList) {
            if(chatroom.getName().equals(chatroomName)) {
                joinedChat = chatroom;
            }
        }
        return joinedChat;
    }

}
