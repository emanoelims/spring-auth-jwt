package dev.emmanoel.springauthjwt.domain.service;

import dev.emmanoel.springauthjwt.domain.Role;
import dev.emmanoel.springauthjwt.domain.User;
import dev.emmanoel.springauthjwt.domain.repository.RoleRepository;
import dev.emmanoel.springauthjwt.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRole(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void roleAddToUser(String username, String roleName) {
        Optional<User> user = getUser(username);
        Optional<Role> role = getRole(roleName);
        if (user.isPresent() && role.isPresent()) {
            user.get().addRole(role.get());
        }
    }
}
