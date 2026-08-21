package com.example.sahil.eventportal.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    String secretKey = "";
    public JwtServiceImpl() throws NoSuchAlgorithmException {
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        }catch(NoSuchAlgorithmException e){
            throw new  NoSuchAlgorithmException(e.getMessage());
        }
    }

    @Override
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .and()
                .signWith(getKey())
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public String getEmail(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    @Override
    public Boolean validateToken(String token, UserDetails user) {
        final String email = getEmail(token);
        return (user.getUsername().equals(email) && !isTokenExpired(token));
    }

    private Date getExpirationDate(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
