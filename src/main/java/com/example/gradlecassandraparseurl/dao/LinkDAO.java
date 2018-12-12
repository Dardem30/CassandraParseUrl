package com.example.gradlecassandraparseurl.dao;

import com.example.gradlecassandraparseurl.model.Link;
import org.springframework.data.repository.CrudRepository;

public interface LinkDAO extends CrudRepository<Link, Long> {
}
