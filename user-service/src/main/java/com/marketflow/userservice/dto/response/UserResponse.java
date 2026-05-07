package com.marketflow.userservice.dto.response;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class UserResponse {

  UUID id;
  String name;
  LocalDateTime createdAt;

}
