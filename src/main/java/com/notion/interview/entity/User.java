package com.notion.interview.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
public class User {
    String name;
    List<String> messages;
}