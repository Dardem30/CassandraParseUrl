package com.example.gradlecassandraparseurl.service;

import com.example.gradlecassandraparseurl.dao.RoleDAO;
import com.example.gradlecassandraparseurl.dao.UserDAO;
import com.example.gradlecassandraparseurl.model.AppUser;
import com.example.gradlecassandraparseurl.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDAO userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleDAO roleDAO;

    @Override
    public final UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(appUser.getUsername(), appUser.getPassword(), Collections.singletonList(appUser.getRole()));
    }

    public final String saveUser(final AppUser user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Sorry but user with this username already exists";
        }
        user.setRole(roleDAO.findByName("ROLE_USER"));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "successfully";
    }

    public final String deleteUserByUsername(final String username) {
        final AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            return "User with this username dose not exist";
        }
        userRepository.delete(appUser);
        return "Deleted";
    }

    public Iterable<Role> getAllRoles() {
        return roleDAO.findAll();
    }

    public Iterable<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
