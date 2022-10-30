package com.example.springportfolio.model;

import com.example.springportfolio.exceptions.InvalidFileTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageStorage {
    @Value("${app.base.path}")
    String basePath;

    public String saveAndReturnUrl(MultipartFile image) throws NullPointerException, IOException{
        if(!image.getContentType().contains("image")) throw new InvalidFileTypeException("file must be image type");

        saveImg(image);

        return generateImgPath(image);
    }

    public void saveImg(MultipartFile image)throws IOException{
        Path directoryImages = Paths.get("src/main/resources/static");
        String absolutePath = directoryImages.toFile().getAbsolutePath();

        byte[] bytesImg = image.getBytes();
        Path fullPath = Paths.get(absolutePath+"/"+image.getOriginalFilename());
        Files.write(fullPath, bytesImg);
    }

    public String generateImgPath(MultipartFile image){
        return basePath+image.getOriginalFilename();
    }

    public void deleteImg(MultipartFile image) throws IOException {
        Path directoryImages = Paths.get("src/main/resources/static");
        String absolutePath = directoryImages.toFile().getAbsolutePath();
        Path fullPath = Paths.get(absolutePath+"/"+image.getOriginalFilename());

        Files.deleteIfExists(fullPath);
    }

    public void deleteImg(String imageName) throws IOException {
        Path directoryImages = Paths.get("src/main/resources/static");
        String absolutePath = directoryImages.toFile().getAbsolutePath();
        Path fullPath = Paths.get(absolutePath+"/"+imageName);

        Files.delete(fullPath);
    }

}
