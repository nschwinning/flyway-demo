package com.example.flywaydemo.web.controller;

import com.example.flywaydemo.data.model.UserEntity;
import com.example.flywaydemo.service.UserService;
import com.example.flywaydemo.web.mapper.UserMapper;
import com.example.flywaydemo.web.resource.UserGetResource;
import com.example.flywaydemo.web.resource.UserPostResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public ResponseEntity<List<UserGetResource>> getAllUsers() {
        return ResponseEntity.ok(userMapper.mapToUserGetResourceList(userService.getAllUsers()));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserGetResource> getUser(@PathVariable long id) {
        Optional<UserEntity> userEntityOptional = userService.getUser(id);
        if (userEntityOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.mapToUserGetResource(userEntityOptional.get()));
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody UserPostResource userPostResource) {
        UserEntity user = userService.createUser(userMapper.mapToUserEntity(userPostResource));
        URI location = linkTo(methodOn(UserController.class).getUser(user.getId())).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
