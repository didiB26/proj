package com.dana.proj.proj.service.persistence;

import com.dana.proj.proj.exception.BadFileNameException;
import com.dana.proj.proj.exception.NameTooLongException;
import com.dana.proj.proj.exception.WritingImageLocalException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class HelperClass {

    public byte[] readingImageSize(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writingImageLocally(String filePath, byte[] imageData){
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(imageData);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new WritingImageLocalException("Cannot acces file");
        }
    }
    private void checkDirectory(String uploadFolder){
        File dir = new File(uploadFolder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

//play around for exceptions
    private void checkFileName(String file){
        if(file.contains("..")){
            throw new BadFileNameException("File Name contains '..'");
        }
    }

//perhaps better treated from frontend rather than here; can also put max size on column in DB table
    private void checkPostName(String name){
        if(name.length()>40){
            throw new NameTooLongException();
        }
    }

    public void allInputCheckers(String name, MultipartFile file, String uploadFolder){
        checkDirectory(uploadFolder);
        checkFileName(file.getOriginalFilename());
        checkPostName(name);
    }
}
