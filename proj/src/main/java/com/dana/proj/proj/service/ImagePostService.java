package com.dana.proj.proj.service;

import com.dana.proj.proj.model.ImagePost;
import com.dana.proj.proj.repository.ImagePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagePostService {
    @Autowired
    private ImagePostRepository imagePostRepository;

    public List<ImagePost> getAllActiveImages() {
        return imagePostRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }


    public ImagePost getImageById(Long id) {
        Optional<ImagePost> imagePostDB = imagePostRepository.findById(id);
        if(imagePostDB.isPresent() ) {
            return imagePostDB.get();
        } else {
            throw new RuntimeException("Image not found with id : " + id);
        }
    }

}
