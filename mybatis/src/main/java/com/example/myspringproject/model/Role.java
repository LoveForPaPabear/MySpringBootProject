package com.example.myspringproject.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Role implements Serializable {

    private static final long serialVersionUID = -2708033201665150257L;

    private Long id;

    private String name;

    private Set<Permission> permissions = new HashSet<>();

    private Set<User> users = new HashSet<>();
}