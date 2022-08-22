package com.dana.proj.proj.service;

import com.dana.proj.proj.model.ImagePost;
import com.dana.proj.proj.repository.ImagePostRepository;
import com.dana.proj.proj.service.persistence.ImagePersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ImagePostServiceImpl implements ImagePostService{
    @Autowired
    private final ImagePostRepository imagePostRepository;

    @Autowired
    private final ImagePersistenceService imageSavingService;

    public void saveImage(String name, MultipartFile file, String uploadFolder){
        imageSavingService.saveImage(name, file, uploadFolder);
    }

    public List<ImagePost> getAllActiveImages() {
        //must modify so it can pull image from local?
        return imagePostRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    //must modify
    public ImagePost getImageById(Long id) {
        Optional<ImagePost> imagePostDB = imagePostRepository.findById(id);
        if (imagePostDB.isPresent()) {
            byte[] imagedata = checkPersistenceImagePost(imagePostDB.get());
            ///blob in database? how?
            imagePostDB.get().setImageSize(imagedata);
            return imagePostDB.get();
        } else {
            throw new NoSuchElementException("Image not found with id : " + id);
        }
    }

    //helper to check how image was stored in db
    public byte[] checkPersistenceImagePost(ImagePost imagePost) {
        byte[] imagedata = null;
        if (imagePost.getImageSize() == null) {
            try {
                imagedata = Files.readAllBytes(Paths.get(imagePost.getImagePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return imagedata;
        } else return imagePost.getImageSize();
    }

    @Override
    public void likeImagePost(Long id) {
        ImagePost imagePost = getImageById(id);
        imagePost.incrementCounting();
        imagePostRepository.save(imagePost);
    }
}
