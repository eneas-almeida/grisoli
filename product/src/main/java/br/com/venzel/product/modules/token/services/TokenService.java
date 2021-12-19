package br.com.venzel.product.modules.token.services;

import static org.springframework.util.ObjectUtils.isEmpty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import br.com.venzel.product.modules.token.dtos.TokenDTO;
import br.com.venzel.product.shared.exceptions.business.AuthorizationException;

@Service
public class TokenService {

    private static final String EMPTY_SPACE = " ";
    private static final Integer TOKEN_INDEX = 1;

    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public void validateAuthorization(String token) {
        
        String accessToken = extractToken(token);

        try {
            var claims = Jwts
                .parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
                .build()
                .parseClaimsJws(accessToken)
                .getBody();

            TokenDTO user = TokenDTO.getUser(claims);
            
            if (isEmpty(user) || isEmpty(user.getId())) {
                throw new AuthorizationException("The user is not valid.");
            }
        } catch (Exception e) {
            throw new AuthorizationException("Error while trying to proccess the Access Token.");
        }
    }

    private String extractToken(String token) {
        if (isEmpty(token)) {
            throw new AuthorizationException("The access token was not informed.");
        }

        if (token.contains(EMPTY_SPACE)) {
            return token.split(EMPTY_SPACE)[TOKEN_INDEX];
        }
        
        return token;
    }
}