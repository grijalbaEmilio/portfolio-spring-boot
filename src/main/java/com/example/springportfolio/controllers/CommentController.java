package com.example.springportfolio.controllers;

import com.example.springportfolio.model.Comment;
import com.example.springportfolio.services.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    CommentService service;

    public CommentController(CommentService service){
        this.service = service;
    }

    @GetMapping("/api/v1/comments")
    public List<Comment> findAll(){
        return service.findAll();
    }

    @GetMapping("/api/v1/comments/author/{authorId}")
    public List<Comment> findByAuthorId(@PathVariable Long authorId){
        return service.findByAuthorId(authorId);
    }
}
