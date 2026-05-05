package com.marketflow.userservice.controller;

import com.marketflow.userservice.dto.request.RegisterRequest;
import com.marketflow.userservice.dto.response.UserResponse;
import com.marketflow.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;
  private static  final Logger logger = LoggerFactory.getLogger(UserController.class);


  @Operation(summary = "Get user by ID")
  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUserById (@PathVariable UUID id){
    UserResponse userResponse = userService.getUserById(id);

    return ResponseEntity.ok(userResponse);
  }

  @PostMapping
  public ResponseEntity<UserResponse> createNewUser(@Valid @RequestBody RegisterRequest user) {
    return ResponseEntity.ok(userService.createUser(user));
  }


  @PatchMapping("/{id}")
  public ResponseEntity<UserResponse> editUser(@RequestBody RegisterRequest user, @PathVariable UUID id){
    return ResponseEntity.ok(userService.updateUser(user, id));
  }

}
