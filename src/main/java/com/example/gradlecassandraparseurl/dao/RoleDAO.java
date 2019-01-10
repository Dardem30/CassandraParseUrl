package com.example.gradlecassandraparseurl.dao;

import com.example.gradlecassandraparseurl.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDAO extends CrudRepository<Role, Long> {
    Role findByName(String name);
}