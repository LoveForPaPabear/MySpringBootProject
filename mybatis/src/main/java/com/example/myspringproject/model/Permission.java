package com.example.myspringproject.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Permission implements Serializable {

    private static final long serialVersionUID = 1433678446529151512L;

    private Long id;
    private String name;
    private String url;
}