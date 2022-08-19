package com.dana.proj.proj.service.persistence;

import org.springframework.web.multipart.MultipartFile;

public interface ImagePersistenceService {
    void saveImage(String name, MultipartFile file, String uploadFolder);

}
