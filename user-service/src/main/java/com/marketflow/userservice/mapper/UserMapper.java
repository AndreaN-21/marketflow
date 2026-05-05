package com.marketflow.userservice.mapper;

import com.marketflow.userservice.dto.request.RegisterRequest;
import com.marketflow.userservice.dto.response.UserResponse;
import com.marketflow.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserResponse toResponse(User user) {
    return UserResponse.builder()
      .id(user.getId())
      .name(user.getFullName())
      .createdAt(user.getCreatedAt())
      .externalId(user.getExternalId())
      .build();

  }


  public User toUser (RegisterRequest dto) {
    return User.builder()
      .email(dto.getEmail())
      .fullName(dto.getFullName())
      .build();
  }
}
