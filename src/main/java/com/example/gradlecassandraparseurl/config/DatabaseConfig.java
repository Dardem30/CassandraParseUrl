package com.example.gradlecassandraparseurl.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@PropertySource("classpath:application.properties")
@EnableMongoRepositories("com.example.gradlecassandraparseurl.dao")
public class DatabaseConfig {

    @Value("${db.mongo.url}")
    private String url;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        final MongoClientURI mongoClientURI = new MongoClientURI(url);
        return new SimpleMongoDbFactory(mongoClientURI);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
