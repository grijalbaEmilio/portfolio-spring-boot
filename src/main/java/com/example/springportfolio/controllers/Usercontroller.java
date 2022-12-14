package com.example.springportfolio.controllers;

import com.example.springportfolio.dto.ResponseLogin;
import com.example.springportfolio.exceptions.*;
import com.example.springportfolio.model.User;
import com.example.springportfolio.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class Usercontroller {

    UserService service;

    public Usercontroller(UserService service){
        this.service = service;
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        try{
            User userSave = service.register(user);
            return ResponseEntity.ok(userSave);
        }
        catch (DuplicateException | LengthException e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }catch (ConstraintViolationException e){
            return ResponseEntity.badRequest().eTag("constraint exception").build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(String email, String password){
        try {
            ResponseLogin response = service.login(email, password);
            return ResponseEntity.ok(response);
        } catch (NotFoundResourceException | InvalidPasswordException e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id, @RequestHeader String token){
        try {
            User userFind = service.findById(id, token);
            return ResponseEntity.ok(userFind);
        }catch (NotFoundResourceException e){
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }catch (NotAuthorizedException e) {
            return ResponseEntity.status(401).eTag(e.getMessage()).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<User>> findAll(@RequestHeader String token){
        try {
            List<User> users = service.findAll(token);
            return ResponseEntity.ok(users);
        } catch (NotAuthorizedException e) {
            return ResponseEntity.status(401).eTag(e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id, @RequestHeader String token){
        try {
            User userDelete = service.deleteById(id, token);
            return ResponseEntity.ok(userDelete);
        } catch (NotFoundResourceException e) {
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        } catch (NotAuthorizedException e){
            return ResponseEntity.status(401).eTag(e.getMessage()).build();
        }
    }

    @PutMapping("")
    public ResponseEntity<User> update(@RequestBody User user, @RequestHeader String token){
        try {
            User userUpdate = service.update(user, token);
            return ResponseEntity.ok(userUpdate);
        } catch (NotFoundResourceException e) {
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        } catch (ConstraintViolationException | TransactionSystemException e){
            return ResponseEntity.badRequest().eTag("constraint exception").build();
        } catch (NotAuthorizedException e ){
            return ResponseEntity.status(401).eTag(e.getMessage()).build();
        }
    }
}
