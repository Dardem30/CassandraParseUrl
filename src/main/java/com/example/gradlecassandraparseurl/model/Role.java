package com.example.gradlecassandraparseurl.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document(collection = "role")
@Data
public class Role implements GrantedAuthority {
    private ObjectId id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
