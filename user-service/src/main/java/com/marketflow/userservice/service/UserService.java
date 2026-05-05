package com.marketflow.userservice.service;

import com.marketflow.userservice.dto.request.RegisterRequest;
import com.marketflow.userservice.dto.response.UserResponse;
import com.marketflow.userservice.entity.User;
import com.marketflow.userservice.exception.ResourceNotFoundException;
import com.marketflow.userservice.mapper.UserMapper;
import com.marketflow.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserResponse getUserById (UUID id){
    log.debug("[getUserById] - begin for id={}", id);

    User user = userRepository.getUserById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));

    log.debug("[getUserById] - end for id={}", id);
    return userMapper.toResponse(user);
  }


  public UserResponse createUser(RegisterRequest request){
    log.debug("[createUser] - begin for request={}", request);

    User user = userMapper.toUser(request);
    userRepository.save(user);

    log.debug("[createUser] - end for request={} userId={}", request, user.getId());
    return userMapper.toResponse(user);
  }

  public UserResponse updateUser(RegisterRequest user, UUID id) {
    User newUser = userRepository.getUserById(id).map(u -> {
        u.setEmail(user.getEmail());
        u.setFullName(user.getFullName());
        return u;
      }).orElseThrow(() -> new ResourceNotFoundException(id));

    return userMapper.toResponse(newUser);

  }
}
