package com.marketflow.userservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@Getter
public class SupabaseConfig {

  @Value("${supabase.url}")
  private String url;

  @Value("${supabase.jwt-secret}")
  private String jwtSecret;

  @Value("${supabase.service-role-key}")
  private String serviceRoleKey;

  @Bean
  public JwtDecoder jwtDecoder() {
    String jwksUri = url + "/auth/v1/.well-known/jwks.json";
    return NimbusJwtDecoder.withJwkSetUri(jwksUri)
      .jwsAlgorithm(SignatureAlgorithm.ES256)
      .build();
  }
}
