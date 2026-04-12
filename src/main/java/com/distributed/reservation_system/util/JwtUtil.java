package com.distributed.reservation_system.util;

import com.distributed.common.exception.AuthException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static String createJWTToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
//                .claim("extraVariable", "premium")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(key)
                .compact();
    }

    public static Claims validateToken(String jwtToken){
        try {
            return  Jwts.parserBuilder()
                    .setSigningKey(key) // Use the same secret used to sign it
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

        } catch (SignatureException e) {
            throw new AuthException("Invalid JWT signature: {}", e.getMessage(), e);
        } catch (MalformedJwtException e) {
            throw new AuthException("Invalid JWT token: {}", e.getMessage(), e);
        } catch (ExpiredJwtException e) {
            throw new AuthException("JWT token is expired: {}", e.getMessage(), e);
        } catch (UnsupportedJwtException e) {
            throw new AuthException("JWT token is unsupported: {}", e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new AuthException("JWT claims string is empty: {}", e.getMessage(), e);
        }
    }

    public static String getUserIdFromToken(String jwtToken) {
        return "";
    }
}
