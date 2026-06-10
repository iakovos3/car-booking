package com.iakovos.user;

import java.util.Optional;
import java.util.UUID;

public class UserDao {

    private User[] users = {
            new User(UUID.fromString("8ca51d2b-aaaf-4bf2-834a-e02964e10fc3"), "Maria"),
            new User(UUID.fromString("b10d126a-3608-4980-9f9c-aa179f5cebc3"), "Alan"),
            new User(UUID.fromString("f7e3d84f-4d92-4809-a7a2-f8dbdfebc956"), "Iakovos")
    };

    public User[] findAllUsers() {
        return users;
    }

    public Optional<User> findById(UUID id) {
        for (User user : users) {
            if (user.id().equals(id))
                return Optional.of(user);
        }

        return Optional.empty();
    }
}
