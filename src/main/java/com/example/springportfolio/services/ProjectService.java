package com.example.springportfolio.services;

import com.example.springportfolio.exceptions.NoDuplicateException;
import com.example.springportfolio.exceptions.NotFoundResource;
import com.example.springportfolio.model.ImageStorage;
import com.example.springportfolio.model.Project;
import com.example.springportfolio.reposiroties.ProjectRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository repository;
    private ImageStorage imageStorage;

    public ProjectService(ProjectRepository repository, ImageStorage imageStorage){
        this.repository = repository;
        this.imageStorage = imageStorage;
    }

    public Project
    save(String title, String description, String codeUrl, String demoUrl, String[] technologies, MultipartFile image) throws IOException {

        if(title == null || image== null || image.isEmpty() || description == null || technologies == null) {
            throw new NullPointerException("for save need minimum image, description and technologies");
        }

        Project project = new Project(null, title,description, null, null, codeUrl, demoUrl);

        String url = imageStorage.saveAndReturnUrl(image);
        if(repository.findByImgUrl(url) != null) throw new NoDuplicateException("already exists the img resource");
        project.setImgUrl(url);
        project.setTechnologies(Arrays.stream(technologies).toList());

        return repository.save(project);
    }

    public List<Project> findAll(){
        return  repository.findAll();
    }

    public Project findById(Long id)throws NotFoundResource{
        var findOptional = repository.findById(id);
        if(findOptional.isEmpty()) throw new NotFoundResource("project not found");
        return findOptional.get();
    }

    public void deleteById(Long id) throws IOException {
        Project project = findById(id);
        String imageName = Arrays
                .stream(project.getImgUrl().split("/"))
                .reduce("", (x, y)->y);
        imageStorage.deleteImg(imageName);
        repository.deleteById(id);
    }

}
