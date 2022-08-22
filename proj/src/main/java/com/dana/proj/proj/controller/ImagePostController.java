package com.dana.proj.proj.controller;

import com.dana.proj.proj.model.ImagePost;
import com.dana.proj.proj.service.ImagePostServiceImpl;
import lombok.RequiredArgsConstructor;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class ImagePostController {

    @Autowired
    private final ImagePostServiceImpl imagePostService;

    @Value("${upload.dir}")
    private String uploadFolder;

    //Temporary API which return a model
    @GetMapping("/")
    public String images(Model map) {
        List<ImagePost> images = imagePostService.getAllActiveImages();
        map.addAttribute("images", images);
        return "images";
    }

    //Temporary API which return a model
    @GetMapping("/addImage")
    public ModelAndView addImage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addImage");
        return modelAndView;
    }

    @GetMapping("/show")
    public ResponseEntity<List<ImagePost>> getAll() {
        return new ResponseEntity<>(imagePostService.getAllActiveImages(), HttpStatus.OK);
    }

    @PostMapping("/submitImage")
    public @ResponseBody ResponseEntity<?> createImage(@RequestParam("name") String name, final @RequestParam("image") MultipartFile file) {
        imagePostService.saveImage(name, file, uploadFolder);
        return new ResponseEntity<>("Image saved!", HttpStatus.OK);
    }

    //to modify this in order to work with images saved locally
    @GetMapping("/display/{id}")
    @ResponseBody
    public void showImage(@PathVariable("id") Long id, HttpServletResponse response) throws ServletException, IOException {
        ImagePost imagePost = imagePostService.getImageById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(imagePost.getImageSize());
        response.getOutputStream().close();
    }

    @GetMapping("/display/post/{id}")
    public ResponseEntity<?> showPost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(imagePostService.getImageById(id), HttpStatus.OK);
    }

}
