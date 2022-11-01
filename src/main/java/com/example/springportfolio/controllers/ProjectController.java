package com.example.springportfolio.controllers;

import com.example.springportfolio.exceptions.InvalidTypeException;
import com.example.springportfolio.exceptions.DuplicateException;
import com.example.springportfolio.exceptions.NotFoundResourceException;
import com.example.springportfolio.model.Project;
import com.example.springportfolio.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
public class ProjectController {
    private ProjectService service;

    public ProjectController(ProjectService service){
        this.service = service;
    }

    @GetMapping("/api/v1/projects")
    public List<Project> findAll(){
        return service.findAll();
    }

    @PostMapping("/api/v1/projects")
    public ResponseEntity<Project>
    save(String title, String description, String codeUrl, String demoUrl, String[] technologies, MultipartFile image) throws IOException {
        try {
            Project projectSaved = service.save(title, description, codeUrl, demoUrl, technologies, image);
            return ResponseEntity.ok(projectSaved);
        } catch (InvalidTypeException | NullPointerException | DuplicateException e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @GetMapping("/api/v1/projects/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (NotFoundResourceException e) {
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }

    @DeleteMapping("/api/v1/projects/{id}")
    public ResponseEntity<Project> deleteById(@PathVariable Long id) throws IOException {
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundResourceException e) {
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/api/v1/projects")
    public ResponseEntity<Project>
    update(Long id, String title, String description, String codeUrl, String demoUrl, String[] technologies, MultipartFile image) throws IOException {
        try{
            Project projectUpdate = service.update(id, title, description, codeUrl, demoUrl, technologies, image);
            return ResponseEntity.ok(projectUpdate);
        }catch (NotFoundResourceException e){
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }catch(InvalidTypeException | NullPointerException | DuplicateException e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }


    }
}
