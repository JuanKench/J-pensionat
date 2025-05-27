package com.example.j_pensionat.enums.templatepath;

import lombok.Getter;

@Getter
public enum RoomTemplatePath {
    LIST("rooms/list");

    private final String path;

    RoomTemplatePath(String path) {
        this.path = path;
    }
}