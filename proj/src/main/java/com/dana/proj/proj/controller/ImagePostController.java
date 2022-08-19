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
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ImagePostController {

    @Autowired
    private final ImagePostServiceImpl imagePostService;

    @Value("${upload.dir}")
    private String uploadFolder;

    //API used to display images in a table; used only temporary, will use RestController instead of Controller
    @GetMapping("/")
    public String images(Model map) {
        List<ImagePost> images = imagePostService.getAllActiveImages();
        map.addAttribute("images", images);
        return "images";
    }

    @GetMapping("/show2")
    public ResponseEntity<List<ImagePost>> getAll() {
        return new ResponseEntity<>(imagePostService.getAllActiveImages(), HttpStatus.OK);
    }

    @GetMapping("/addImage")
    public ModelAndView addImage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addImage");
        return modelAndView;
    }

    @PostMapping("/submitImage")
    public @ResponseBody ResponseEntity<?> createImage(@RequestParam("name") String name, final @RequestParam("image") MultipartFile file) {
        File dir = new File(uploadFolder);
        try {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //to modify this - display error and response entity - ;runtime
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        imagePostService.saveImage(name, file, uploadFolder);

            //vreau reddirect - nu merge -- treaba de jos e folosita vreodata?
            HttpHeaders header = new HttpHeaders();
            header.add("Location", "/");
            //return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
            return new ResponseEntity<>(header, HttpStatus.OK);
        }

    @GetMapping("/display/{id}")
    @ResponseBody
    public void showImage(@PathVariable("id") Long id, HttpServletResponse response) throws ServletException, IOException {
        ImagePost imagePost = imagePostService.getImageById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(imagePost.getImageSize());
        response.getOutputStream().close();
    }

    ///Not implemented fully yet
    @GetMapping("/display/post/{id}")
    public String showPost(@PathVariable("id") Long id, HttpServletResponse response, Model model) throws ServletException, IOException {
        ImagePost imagePost = imagePostService.getImageById(id);
        model.addAttribute("imagePost", imagePost);
        return "imagePost";
    }


}
