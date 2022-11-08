package com.example.springportfolio.services;

import com.example.springportfolio.exceptions.NotAuthorizedException;
import com.example.springportfolio.model.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUserService {

    @Value("${jwtSecret}")
    String jwtSecret;
    @Value("${jwtExpirationMs}")
    String jwtExpirationMs;


    public String generateAccessToken(User user){

        if(user == null) throw new NullPointerException("payload not must be null");

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("id", user.getId());
        payload.put("name", user.getName());
        payload.put("lastname", user.getLastName());
        payload.put("email", user.getEmail());
        payload.put("password", user.getPassword());
        payload.put("role", user.getRole());

        return  Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + Integer.parseInt(jwtExpirationMs)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public User validateAccessToken(String token) throws NotAuthorizedException {

        try{
            Map<String, Object> userClaims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            Long id = ((Integer)userClaims.get("id")).longValue();
            User user = new User(id, null, null, null, null, null);
            return user
                    .setPassword((String) userClaims.get("password"))
                    .setEmail((String) userClaims.get("email"))
                    .setName((String) userClaims.get("name"))
                    .setRole((String) userClaims.get("role"))
                    .setLastName((String) userClaims.get("lastname"));

        }catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException | UnsupportedJwtException e){
            throw new NotAuthorizedException("Json Web Token is invalid");
        }

    }

    public User adminValidateAccessToken(String token) throws NotAuthorizedException {
        User user = validateAccessToken(token);
        if(!user.getRole().toString().equals("ADMIN")) throw new NotAuthorizedException("user is not administrator");
        return user;
    }
}
