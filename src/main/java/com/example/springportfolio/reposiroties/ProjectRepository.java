package com.example.springportfolio.reposiroties;

import com.example.springportfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByImgUrl(String imgUrl);
}
