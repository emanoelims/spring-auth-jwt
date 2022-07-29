package dev.emmanoel.springauthjwt.domain.service;

import dev.emmanoel.springauthjwt.domain.Role;
import dev.emmanoel.springauthjwt.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUser(String username);

    List<User> getUsers();

    Role saveRole(Role role);

    Optional<Role> getRole(String name);

    void roleAddToUser(String username, String roleName);
}
