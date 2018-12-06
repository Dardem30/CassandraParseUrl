package com.example.gradlecassandraparseurl.da;

import com.example.gradlecassandraparseurl.bo.HrefModel;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface HrefModelRepository extends CassandraRepository<HrefModel, Long> {

}
