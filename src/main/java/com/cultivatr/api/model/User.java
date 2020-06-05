package com.cultivatr.api.model;

import java.util.Objects;

public class User {

    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        User user = (User) object;
        return  Objects.equals(this.name, user.name) &&
                Objects.equals(this.email, user.email);
    }
}
