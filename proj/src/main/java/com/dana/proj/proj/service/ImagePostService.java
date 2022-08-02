package com.dana.proj.proj.service;

import com.dana.proj.proj.model.ImagePost;
import com.dana.proj.proj.repository.ImagePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ImagePostService {
    @Autowired
    private ImagePostRepository imagePostRepository;

    public void saveImage(ImagePost imageGallery) {
        imagePostRepository.save(imageGallery);
    }

    public List<ImagePost> getAllActiveImages() {
        return imagePostRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Optional<ImagePost> getImageById(Long id) {
        return imagePostRepository.findById(id);
    }

}
