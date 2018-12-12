package com.example.gradlecassandraparseurl.controller;

import com.example.gradlecassandraparseurl.controller.dto.RequestForm;
import com.example.gradlecassandraparseurl.service.ParseUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ParserController {
    private final ParseUrlService parseUrlService;

    @PostMapping(value = "/parseUrlForAdvertisements")
    public final ResponseEntity<List<String>> parseUrlForAdvertisements(final @RequestBody RequestForm url) throws IOException {
        return ResponseEntity.ok(parseUrlService.parseForAdvertisements(url.getUrl()));
    }
    @PostMapping(value = "/typeOfLinks")
    public final ResponseEntity typeOfLinks(final @RequestBody RequestForm url) throws IOException {
        return ResponseEntity.ok(parseUrlService.parseForType(url.getUrl()));
    }
    @PostMapping(value = "/isImage")
    public final ResponseEntity isImage(final @RequestBody RequestForm url) throws IOException {
        return ResponseEntity.ok(parseUrlService.isImage(url.getUrl()));
    }
    @PostMapping(value = "/getAllLinkedImages")
    public final ResponseEntity getAllLinkedImages(final @RequestBody RequestForm url) throws IOException {
        return ResponseEntity.ok(parseUrlService.getAllAdvertisementsLinkedImages(url.getUrl()));
    }
}