package com.example.gradlecassandraparseurl.controller;

import com.example.gradlecassandraparseurl.service.ParseUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ParserController {
    private final ParseUrlService parseUrlService;

    @PostMapping(value = "/parseUrl")
    public final ResponseEntity<String> parseUrl(final @RequestParam("url") String url) throws IOException {
        return ResponseEntity.ok(parseUrlService.parseUrl(url));
    }
}