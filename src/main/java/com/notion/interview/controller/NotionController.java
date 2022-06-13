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

    NotionController() {
        c1 = new Chatroom();
        List<User> users = new ArrayList<>();
        User user1 = new User();
        users.add(user1);
        c1.setUsers(users);
        c1.setName("c1");
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
        Chatroom joinedChat = new Chatroom();
        for (Chatroom chatroom: chatroomsList) {
            if(chatroom.getName() == chatroomName) {
                joinedChat = chatroom;
            }
        }

        return joinedChat.addUser(user);
    }

    /**
     * After joining, a user can send a message to that chat room.
     */
    @PostMapping("/messages/{chatroomName}/{message}")
    public void sendMessage(@RequestBody User user, @PathVariable String message, @PathVariable String chatroomName) {
        // Instantiate new Message object
        // assign user to message
        // at this point should have Message {"message1", user1}
        // add message to chatroom
    }

    /**
     * A user can retrieve messages from a chat room that they have joined.
     */
    @GetMapping("/messages/{chatroomName}")
    public List<Message> retrieveMessages(@PathVariable String chatroomName) {
        Chatroom joinedChat = new Chatroom();
        for (Chatroom chatroom: chatroomsList) {
            if(chatroom.getName() == chatroomName) {
                joinedChat = chatroom;
            }
        }
        return joinedChat.getMessages();
    }

    /**
     * Can leave a chatroom
     */
    @DeleteMapping("/leave/{chatroomName}")
    public void leaveChatroom(@RequestBody User user, @PathVariable String chatroomName) {
        Chatroom joinedChat = new Chatroom();
        for (Chatroom chatroom: chatroomsList) {
            if(chatroom.getName() == chatroomName) {
                joinedChat = chatroom;
            }
        }
        // remove user from chatroom list of users object
    }

}
