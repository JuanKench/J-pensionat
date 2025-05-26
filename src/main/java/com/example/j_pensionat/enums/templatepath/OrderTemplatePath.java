package com.example.j_pensionat.enums.templatepath;

import lombok.Getter;

@Getter
public enum OrderTemplatePath {
    DETAILS("orders/details"),
    MANAGE("/orders/manage"),
    CREATE("orders/create"),
    EDIT("orders/edit");

    private final String path;

    OrderTemplatePath(String path) {
        this.path = path;
    }
}