package com.example.gradlecassandraparseurl.controller;

import com.example.gradlecassandraparseurl.controller.dto.RequestForm;
import com.example.gradlecassandraparseurl.model.Role;
import com.example.gradlecassandraparseurl.service.ParseUrlService;
import com.example.gradlecassandraparseurl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin/")
@RequiredArgsConstructor
public class AdminController {
    private final ParseUrlService parseUrlService;
    private final UserService userService;

    @GetMapping(value = "getParseHistory")
    public final ResponseEntity getParseHistory() {
        return ResponseEntity.ok(parseUrlService.parseHistory());
    }

    @DeleteMapping(value = "clearHistory")
    public final String clearHistory() {
        parseUrlService.clearHistory();
        return "clean";
    }
    @DeleteMapping(value = "deleteUser/{username}")
    public final String deleteUser(@PathVariable final String username) {
        return userService.deleteUserByUsername(username);
    }
    @PostMapping(value = "/getAllLinkedImages")
    public final ResponseEntity getAllLinkedImages(final @RequestBody RequestForm url) {
        return ResponseEntity.ok(parseUrlService.getAllAdvertisementsLinkedImages(url.getUrl(), url.isPrivate(), url.getIp()));
    }
    @GetMapping(value = "/getAllRoles")
    public final ResponseEntity getAllRoles() {
        return ResponseEntity.ok(userService.getAllRoles());
    }

    @GetMapping(value = "/getAllUsers")
    public final ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
