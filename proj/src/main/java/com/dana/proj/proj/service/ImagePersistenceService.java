package com.dana.proj.proj.service;

import com.dana.proj.proj.model.ImagePost;
import org.springframework.web.multipart.MultipartFile;

public interface ImagePersistenceService {
    void saveImage(String name, MultipartFile file, String uploadFolder);

}
