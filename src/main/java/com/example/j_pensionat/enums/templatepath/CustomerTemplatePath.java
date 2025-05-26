package com.example.j_pensionat.enums.templatepath;

import lombok.Getter;

@Getter
public enum CustomerTemplatePath {
    DETAILS("/customers/customers-details"),
    LIST("/customers/customers-list"),
    CREATE("/customers/customers-new-form"),
    EDIT("customers/customers-edit-form");

    private final String path;

    CustomerTemplatePath(String path) {
        this.path = path;
    }
}
