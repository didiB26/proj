package com.dana.proj.proj.service.persistence;

import com.dana.proj.proj.model.ImagePost;
import com.dana.proj.proj.repository.ImagePostRepository;
import com.dana.proj.proj.service.persistence.HelperClass;
import com.dana.proj.proj.service.persistence.ImagePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Paths;
import java.util.Date;
@RequiredArgsConstructor
@Service
@Transactional
@ConditionalOnProperty(prefix = "image", name = "persistence.database", havingValue = "false")
public class ImageSavingLocalImpl implements ImagePersistenceService {

    @Autowired
    private final ImagePostRepository imagePostRepository;
    @Autowired
    private final HelperClass helperClassMethods;
    @Override
    public void saveImage(String name, MultipartFile file, String uploadFolder) {
        helperClassMethods.allInputCheckers(name, file, uploadFolder);
        //creating objects
        ImagePost imagePost = new ImagePost();
        Date createDate = new Date();
        byte[] imageData = helperClassMethods.readingImageSize(file);
        //pulling all data needed for imagePost setters
        String fileName = file.getOriginalFilename();
        String filePath = Paths.get(uploadFolder, fileName).toString();

        //setting imagePost info's
        imagePost.setName(name);
        //set imageSize to null
        imagePost.setImageSize(null);
        imagePost.setCreateDate(createDate);
        imagePost.setImagePath(filePath);

        //saving locally the image
        helperClassMethods.writingImageLocally(filePath, imageData);

        imagePostRepository.save(imagePost);

    }

}
