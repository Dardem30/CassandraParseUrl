package com.example.gradlecassandraparseurl.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "link")
@Data
public class Link {
    private ObjectId id;
    private String url;
    private String rootUrl;
    private LocalDateTime createTime;
    private String ip;

    public Link(final String url,final String rootUrl,final String ip) {
        this.url = url;
        this.rootUrl = rootUrl;
        this.createTime = LocalDateTime.now();
        this.ip = ip;
    }
}
