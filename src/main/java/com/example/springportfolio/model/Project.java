package com.example.springportfolio.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "project_tbl")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String title;
    private String description;
    private String technologies;
    private String imgUrl;
    private String codeUrl;
    private String demoUrl;

    public Project(){}

    public Project(Long id, String title, String description, String technologies, String imgUrl, String codeUrl, String demoUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.technologies = technologies;
        this.imgUrl = imgUrl;
        this.codeUrl = codeUrl;
        this.demoUrl = demoUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTechnologies() {
        List<String> technologiesArray = (List<String>) Arrays
                .stream(technologies.split(","))
                .filter(x -> x.length() > 0)
                .toList();

        return technologiesArray;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies
                .stream()
                .reduce("", (x, y) -> x+","+y);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public String getImageName(){
        return Arrays
                .stream(imgUrl.split("/"))
                .reduce("", (x, y)->y);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", technologies='" + technologies + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", codeUrl='" + codeUrl + '\'' +
                ", demoUrl='" + demoUrl + '\'' +
                '}';
    }
}
