package com.springboot.blog.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // generate JWT token

//    public String generateToken(Authentication authentication){
//        String username = authentication.getName();
//
//        Date currentDate = new Date();
//
//        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(expireDate)
//                .signWith(SignatureAlgorithm.HS512,jwtSecret)
//                .compact();
//
//        return token;
//
//    }
//
//    // get username from JWT token
//    public String getUsername(String token){
//
//        Claims claims=Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//
//    }
//
//
//    public boolean validateToken(String token){
//        try{
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        }catch (SignatureException ex){
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
//        }catch (ExpiredJwtException expiredJwtException){
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
//        }catch (UnsupportedJwtException unsupportedJwtException){
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
//        }catch (IllegalArgumentException illegalArgumentException){
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Jwt claims string is null or empty");
//        }
//    }


    // generate JWT token
    public String generateToken(Authentication authentication){

        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);


        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // get username from JWT token
    public String getUsername(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token) {

        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;
    }


}
