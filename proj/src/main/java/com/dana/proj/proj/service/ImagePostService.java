package com.dana.proj.proj.service;

import com.dana.proj.proj.model.ImagePost;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagePostService {
    void saveImage(String name, MultipartFile file, String uploadFolder);
    List<ImagePost> getAllActiveImages();
    ImagePost getImageById(Long id);
    void likeImagePost(Long id);
}
