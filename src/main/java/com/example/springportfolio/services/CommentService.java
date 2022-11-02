package com.example.springportfolio.services;

import com.example.springportfolio.exceptions.NotFoundResourceException;
import com.example.springportfolio.model.Comment;
import com.example.springportfolio.reposiroties.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    CommentRepository repository;

    public CommentService(CommentRepository repository, UserService userService){
        this.repository = repository;
    }

    public List<Comment> findAll(){
        return repository.findAll();
    }

    public List<Comment> findByAuthorId(Long AuthorId) {
        List<Comment> comments = findAll();

        return  comments.stream()
                .filter(x -> x.getAuthor().getId().equals(AuthorId))
                .toList();
    }
}
