package com.dana.proj.proj.service;

import com.dana.proj.proj.dtos.CommentDTO;
import com.dana.proj.proj.model.ImageComment;
import com.dana.proj.proj.model.ImagePost;
import com.dana.proj.proj.repository.CommentRepository;
import com.dana.proj.proj.repository.ImagePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final ImagePostRepository imagePostRepository;

    @Override
    public void saveComment(CommentDTO commentDto) {
        ImageComment comment = new ImageComment();
        comment.setCreateDate(new Date());
        ImagePost imagePost = imagePostRepository.getReferenceById(commentDto.getImageId());
        comment.setImagePost(imagePost);
        comment.setTextComm(commentDto.getText());
        commentRepository.save(comment);
    }
}
