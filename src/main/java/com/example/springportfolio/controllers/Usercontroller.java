package com.example.springportfolio.controllers;

import com.example.springportfolio.exceptions.DuplicateException;
import com.example.springportfolio.exceptions.InvalidModifyData;
import com.example.springportfolio.exceptions.LengthException;
import com.example.springportfolio.exceptions.NotFoundResourceException;
import com.example.springportfolio.model.User;
import com.example.springportfolio.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
public class Usercontroller {

    UserService service;

    public Usercontroller(UserService service){
        this.service = service;
    }


    @PostMapping("/api/v1/users")
    public ResponseEntity<User> save(@RequestBody User user){
        try{
            User userSave = service.save(user);
            return ResponseEntity.ok(userSave);
        }
        catch (DuplicateException | LengthException e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }catch (ConstraintViolationException e){
            return ResponseEntity.badRequest().eTag("constraint exception").build();
        }
    }

    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        try {
            User userFind = service.findById(id);
            return ResponseEntity.ok(userFind);
        }catch (NotFoundResourceException e){
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }

    @GetMapping("/api/v1/users")
    public List<User> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/api/v1/users/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        try {
            User userDelete = service.deleteById(id);
            return ResponseEntity.ok(userDelete);
        } catch (NotFoundResourceException e) {
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/api/v1/users")
    public ResponseEntity<User> update(@RequestBody User user){
        try {
            User userUpdate = service.update(user);
            return ResponseEntity.ok(userUpdate);
        } catch (NotFoundResourceException e) {
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        } catch (InvalidModifyData e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }catch (ConstraintViolationException | TransactionSystemException e){
            return ResponseEntity.badRequest().eTag("constraint exception").build();
        }
    }
}
