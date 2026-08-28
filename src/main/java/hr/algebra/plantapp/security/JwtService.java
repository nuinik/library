package hr.algebra.plantapp.security;

import hr.algebra.plantapp.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey secretKey;
    private final long accessExpiration;
    private final long refreshExpiration;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.access-expiration}") long accessExpiration,
                      @Value("${jwt.refresh-expiration}") long refreshExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
    }

    public String generateAccessToken(AppUser user) {
        return generateToken(user, accessExpiration, "ACCESS");
    }

    public String generateRefreshToken(AppUser user) {
        return generateToken(user, refreshExpiration, "REFRESH");
    }

    private String generateToken(AppUser user, long expiration, String tokenType) {
        Date currentTime = new Date();
        Date expirationTime = new Date(currentTime.getTime() + expiration);
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role", user.getRole().name())
                .claim("tokenType", tokenType)
                .issuedAt(currentTime)
                .expiration(expirationTime)
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractTokenType(String token) {
        return extractClaims(token).get("tokenType", String.class);
    }

    public boolean isValid(String token, AppUser user) {
        try {
            return extractUsername(token).equals(user.getUsername())
                    && extractClaims(token).getExpiration().after(new Date());
        } catch (Exception exception) {
            return false;
        }
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload();
    }
}
