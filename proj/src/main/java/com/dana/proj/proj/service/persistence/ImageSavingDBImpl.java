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
@ConditionalOnProperty(prefix = "image", name = "persistence.database", havingValue = "true")
public class ImageSavingDBImpl implements ImagePersistenceService {

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
        //pulling all data needed for imagePost setters
        byte[] imageData = helperClassMethods.readingImageSize(file);

        //setting imagePost info's
        imagePost.setName(name);
        imagePost.setImageSize(imageData);
        imagePost.setCreateDate(createDate);
        //set imagePath to null
        imagePost.setImagePath(null);

        imagePostRepository.save(imagePost);
    }

}
