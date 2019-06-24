package com.example.myspringproject.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -1274150329920794903L;

    private String pkId;

    private String username;

    private String password;

    private int pageNum;

    private int pageSize;

}
