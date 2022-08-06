package com.dana.proj.proj.controller;

import com.dana.proj.proj.model.ImagePost;
import com.dana.proj.proj.service.ImagePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ImagePostController {

    @Autowired
    private ImagePostService imagePostService;

    @Value("${uploadDir}")
    private String uploadFolder;

    @GetMapping("/")
    public String images(Model map) {
        List<ImagePost> images = imagePostService.getAllActiveImages();
        map.addAttribute("images", images);
        return "images";
    }

    ///array cu imagnile descendent pt verif;
    @GetMapping("/show2")
    public ResponseEntity<List<ImagePost>> getAll() {
        return new ResponseEntity<>(imagePostService.getAllActiveImages(), HttpStatus.OK);
    }

    @GetMapping("/addImage")
    public ModelAndView addImage () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addImage");
        return modelAndView;
    }

    @PostMapping("/submitImage")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("name") String name,
                                                         Model model, HttpServletRequest request,
                                                         final @RequestParam("image") MultipartFile file) {
        try {
            String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
            //System.out.println(uploadDirectory);

            //String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            //System.out.println(uploadDirectory);//C:\Users\didiBalasic\AppData\Local\Temp\tomcat-docbase.8080.1944651520665211054\resources\static\images
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            String[] names = name.split(",");
            Date createDate = new Date();

            try {
                File dir = new File(uploadDirectory);
                //nu prea e necesar?
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            ImagePost imagePost = new ImagePost();
            imagePost.setName(names[0]);
            imagePost.setImageSize(imageData);
            imagePost.setDate(createDate);
            imagePostService.saveImage(imagePost);
            //vreau reddirect - nu merge -- treaba de jos e folosita vreodata?
            HttpHeaders header = new HttpHeaders();
            header.add("Location", "/");
            //return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
            return new ResponseEntity<>(header, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/display/{id}")
    @ResponseBody
    public void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ImagePost> imagePost)
            throws ServletException, IOException {
        imagePost = imagePostService.getImageById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(imagePost.get().getImageSize());
        response.getOutputStream().close();
    }




}
