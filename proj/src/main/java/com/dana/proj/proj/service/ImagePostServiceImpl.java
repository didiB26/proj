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
            checkPersistenceImagePost(imagePostDB.get());
            return imagePostDB.get();
        } else {
            throw new NoSuchElementException("Image not found with id : " + id);
        }
    }

    //helper to check how image was stored in db
    public void checkPersistenceImagePost(ImagePost imagePost) {
        byte[] imagedata = null;
        if (imagePost.getImageSize() == null) {
           // imagedata = readingImageSize(new File(imagePost.getImagePath()));
            /*
            BufferedInputStream bufferedInputStream = null;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(imagePost.getImagePath()));
                bufferedInputStream.read(imagedata);
                bufferedInputStream.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


             */

        }
        //return imagedata;

    }
}
