package br.com.venzel.product.modules.token.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String id;
    private String role;
    private String activated;

    public static TokenDTO getUser(Claims tokenClaims) {
        
        try {
            return new ObjectMapper().convertValue(tokenClaims.get("auth"), TokenDTO.class);
        } catch (Exception e) {
            return null;
        }
    }
}
