package com.marketflow.userservice.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(java.util.UUID id) {
    super("User not found with id: " + id);
  }

}
