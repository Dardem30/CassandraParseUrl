package com.example.gradlecassandraparseurl.dao;

import com.example.gradlecassandraparseurl.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}