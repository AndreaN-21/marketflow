package com.marketflow.userservice.controller;

import com.marketflow.userservice.dto.request.RegisterRequest;
import com.marketflow.userservice.dto.request.UpdateRequest;
import com.marketflow.userservice.dto.response.UserResponse;
import com.marketflow.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getMe(@AuthenticationPrincipal Jwt jwt){
    UUID userId = UUID.fromString(jwt.getSubject());
    log.debug("[getMe] - Begin for userId={}", userId);
    UserResponse userResponse = userService.getUserById(userId);
    log.debug("[getMe] - End for userId={}", userId);
    return ResponseEntity.ok(userResponse);
  }

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
  public ResponseEntity<UserResponse> editUser(@RequestBody UpdateRequest user, @PathVariable UUID id){
    return ResponseEntity.ok(userService.updateUser(user, id));
  }

  @DeleteMapping("/{id}")
  public  ResponseEntity<Void> deleteUser(@PathVariable UUID id){
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }


}
