package com.notion.interview.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String content;
    private User user;
}
