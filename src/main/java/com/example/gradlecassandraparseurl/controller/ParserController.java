package com.example.gradlecassandraparseurl.controller;

import com.example.gradlecassandraparseurl.service.ParseUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParserController {
    private final ParseUrlService parseUrlService;

    @PostMapping(value = "/parseUrlForAdvertisements")
    public final ResponseEntity<List<String>> parseUrlForAdvertisements(final @RequestParam("url") String url) throws IOException {
        return ResponseEntity.ok(parseUrlService.parseForAdvertisements(url));
    }
    @PostMapping(value = "/typeOfLinks")
    public final ResponseEntity typeOfLinks(final @RequestParam("url") String url) throws IOException {
        return ResponseEntity.ok(parseUrlService.parseForType(url));
    }
}