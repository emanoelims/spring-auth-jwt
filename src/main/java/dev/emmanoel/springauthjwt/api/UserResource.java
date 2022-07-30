package dev.emmanoel.springauthjwt.api;

import dev.emmanoel.springauthjwt.domain.Role;
import dev.emmanoel.springauthjwt.domain.User;
import dev.emmanoel.springauthjwt.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder uriBuilder) {
        final User savedUser = userService.saveUser(user);
        uriBuilder.path("/api/users/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        final Optional<User> userOptional = userService.getUser(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userOptional.get());
    }

    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@RequestBody Role role, UriComponentsBuilder uriBuilder) {
        final Role savedRole = userService.saveRole(role);
        final URI uri = uriBuilder.path("/api/roles/{id}")
            .buildAndExpand(savedRole.getId())
            .toUri();
        return ResponseEntity.created(uri).body(savedRole);
    }

    @GetMapping("/roles/{roleName}")
    public ResponseEntity<?> getRoles(@PathVariable String roleName) {
        final Optional<Role> roleOptional = userService.getRole(roleName);
        if (roleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleOptional);
    }

}
