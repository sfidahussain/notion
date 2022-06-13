package com.notion.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewApplication {
    /**
     * Implement a set of APIs that can accomplish the following:
     *
     * - A user can join a chat room
     * - After joining, a user can send a message to that chat room.
     * - A user can retrieve messages from a chat room that they have joined.
     * - (Optional) A user can leave a chat room.
     *
     * The server should support multiple chat rooms.
     *
     * User Entity
     * ======
     * String name;
     *
     * Controller
     * =======
     * join
     *
     *
     * Take into account this API will be consumed by a basic chat app (think: slack, discord, basecamp, etc).
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }

}
