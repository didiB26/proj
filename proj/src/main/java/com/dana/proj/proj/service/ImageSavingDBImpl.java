package com.dana.proj.proj.service;

import com.dana.proj.proj.model.ImagePost;
import com.dana.proj.proj.repository.ImagePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.Date;


@Service
@ConditionalOnProperty(prefix = "image", name = "persistence.database", havingValue = "true")
public class ImageSavingDBImpl implements ImagePersistenceService {

    @Autowired
    private ImagePostRepository imagePostRepository;

    @Autowired
    private HelperClass helperClassMethods;

    @Override
    public void saveImage(String name, MultipartFile file, String uploadFolder) {
        //creating objects
        ImagePost imagePost = new ImagePost();
        Date createDate = new Date();
        //pulling all data needed for imagePost setters
        byte[] imageData = helperClassMethods.readingImageSize(file);
        String fileName = file.getOriginalFilename();
        String filePath = Paths.get(uploadFolder, fileName).toString();

        //setting imagePost info's
        imagePost.setName(name);
        imagePost.setImageSize(imageData);
        imagePost.setDate(createDate);
        //I set imageSize to null
        imagePost.setImagePath(null);

        imagePostRepository.save(imagePost);
    }

}