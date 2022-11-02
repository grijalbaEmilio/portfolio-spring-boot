package com.example.springportfolio.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment_tbl")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date date_post;
    @ManyToOne
    private Comment parent;
    @ManyToOne
    private User author;

    public Comment(){}

    public Comment(Long id, String content, Date date_post, Comment parent, User author) {
        this.id = id;
        this.content = content;
        this.date_post = date_post;
        this.parent = parent;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate_post() {
        return date_post;
    }

    public void setDate_post(Date date_post) {
        this.date_post = date_post;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date_post=" + date_post +
                ", parent=" + parent +
                ", author=" + author +
                '}';
    }
}
