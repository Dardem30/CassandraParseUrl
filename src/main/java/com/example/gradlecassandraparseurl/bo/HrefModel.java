package com.example.gradlecassandraparseurl.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("hrefmodel")
@Data
public class HrefModel {
    @PrimaryKey
    private UUID id;
    private String url;

    public HrefModel(String url) {
        this.id = UUID.randomUUID();
        this.url = url;
    }
}
