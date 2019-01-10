package com.example.gradlecassandraparseurl.controller;

import com.example.gradlecassandraparseurl.controller.dto.RequestForm;
import com.example.gradlecassandraparseurl.service.ParseUrlService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.descriptor.web.ContextHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ParserController {
    private final ParseUrlService parseUrlService;

//    @PostMapping(value = "/parseUrlForAdvertisements")
//    public final ResponseEntity<List<String>> parseUrlForAdvertisements(final @RequestBody RequestForm url) {
//        return ResponseEntity.ok(parseUrlService.parseForAdvertisements(url.getUrl()));
//    }
    @PostMapping(value = "/typeOfLinks")
    public final ResponseEntity typeOfLinks(final @RequestBody RequestForm url) {
        return ResponseEntity.ok(parseUrlService.parseForType(url.getUrl()));
    }
    @PostMapping(value = "/isImage")
    public final ResponseEntity isImage(final @RequestBody RequestForm url) {
        return ResponseEntity.ok(parseUrlService.isImage(url.getUrl()));
    }
    @PostMapping(value = "/getAllLinkedImages")
    public final ResponseEntity getAllLinkedImages(final @RequestBody RequestForm url) {
        url.setIsPrivate(false);
        return ResponseEntity.ok(parseUrlService.getAllAdvertisementsLinkedImages(url.getUrl(), url.isPrivate(), url.getIp()));
    }
}