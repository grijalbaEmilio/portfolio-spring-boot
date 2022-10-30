package com.example.springportfolio.controllers;

import com.example.springportfolio.exceptions.InvalidFileTypeException;
import com.example.springportfolio.exceptions.NoDuplicateException;
import com.example.springportfolio.exceptions.NotFoundResource;
import com.example.springportfolio.model.Project;
import com.example.springportfolio.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.URI;
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
        } catch (InvalidFileTypeException | NullPointerException | NoDuplicateException e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @GetMapping("/api/v1/projects/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (NotFoundResource e) {
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }

    // TODO: need delete img from server
    @DeleteMapping("/api/v1/projects/{id}")
    public ResponseEntity<Project> deleteById(@PathVariable Long id){
        try {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundResource e) {
            return ResponseEntity.notFound().eTag(e.getMessage()).build();
        }
    }
}
