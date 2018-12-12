package com.example.gradlecassandraparseurl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "link")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Link {
    private ObjectId id;
    private String url;
    private String rootUrl;

    public Link(String url, String rootUrl) {
        this.url = url;
        this.rootUrl = rootUrl;
    }
}
