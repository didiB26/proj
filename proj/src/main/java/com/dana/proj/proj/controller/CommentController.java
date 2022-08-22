package com.dana.proj.proj.controller;

import com.dana.proj.proj.dtos.CommentDTO;
import com.dana.proj.proj.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor

public class CommentController {

    @Autowired
    private final CommentServiceImpl commentService;

    @PostMapping("/submitComment")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDto) {
        commentService.saveComment(commentDto);
        return new ResponseEntity<>("Comment saved for post with id " + commentDto.getImageId(), HttpStatus.OK);
    }


}
