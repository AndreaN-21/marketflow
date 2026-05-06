
package com.marketflow.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfig {

  @Value("${supabase.jwt-secret}")
  private String jwtSecret;

  @Bean
  public JwtDecoder jwtDecoder() {
    // Supabase espone il secret come stringa plain — usiamo i bytes UTF-8 direttamente
    // NON fare Base64.decode() — il secret di Supabase non è base64
    SecretKey key = new SecretKeySpec(
      jwtSecret.getBytes(StandardCharsets.UTF_8),
      "HmacSHA256"
    );
    return NimbusJwtDecoder.withSecretKey(key).build();
  }
}
