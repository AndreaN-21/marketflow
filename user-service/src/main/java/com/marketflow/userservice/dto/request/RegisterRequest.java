package com.marketflow.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

  @NotBlank
  @Size(min=2, max = 20, message = "Full name must be between 2 and 20")
  private String fullName;

  @NotBlank(message = "Email is required")
  @Email(message = "Email is invalid")
  private String email;


}
