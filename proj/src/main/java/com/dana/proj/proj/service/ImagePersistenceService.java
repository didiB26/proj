package com.dana.proj.proj.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImagePersistenceService {
    void saveImage(String name, MultipartFile file, String uploadFolder);

}
