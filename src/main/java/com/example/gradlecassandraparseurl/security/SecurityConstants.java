package com.example.gradlecassandraparseurl.security;

public interface SecurityConstants {
    String SECRET = "secret";
    String HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    long EXPIRATION_TIME = 864_000_000; // 10 days
}