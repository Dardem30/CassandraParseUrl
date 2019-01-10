package com.example.gradlecassandraparseurl.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
public class AppUser {
    private ObjectId id;
    private String username;
    private String password;
    @DBRef
    private Role role;
}
