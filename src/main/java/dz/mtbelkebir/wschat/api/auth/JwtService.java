package dz.mtbelkebir.wschat.api.auth;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final String pk = "4uBKP2tXIhN2vAabN0hpg+RmUpMbR9jQCfsXx8W8gLYbxoo2JYH2Yupdw8BziycS";

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractIat(String token) {
        return extractClaim(token, Claims::getIssuedAt);
    }

    public Date extractExp(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // +24 hours
            .subject(userDetails.getUsername())
            .signWith(getSigningKey())
            .compact();
    }

    public boolean isTokenExpired(String token) {
        return extractExp(token).before(new Date(System.currentTimeMillis()));
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractSubject(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(pk);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
