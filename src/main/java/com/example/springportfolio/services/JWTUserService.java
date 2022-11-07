package com.example.springportfolio.services;

import com.example.springportfolio.exceptions.InvalidTokenException;
import com.example.springportfolio.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUserService {

    @Value("jwtSecret")
    private String jwtSecret;


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
                //.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS384, jwtSecret)
                .compact();
    }

    public User validateAccessToken(String token) throws InvalidTokenException {

        try{
            Map<String, Object> userClaims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            User user = new User((Long) userClaims.get("id"), null, null, null, null, null);
            return user
                    .setPassword((String) userClaims.get("password"))
                    .setEmail((String) userClaims.get("email"))
                    .setName((String) userClaims.get("name"))
                    .setRole((String) userClaims.get("role"))
                    .setLastName((String) userClaims.get("lastname"));

        }catch (Exception e){
            throw new InvalidTokenException("Json Web Token is invalid");
        }

    }
}
