package com.dana.proj.proj.service;

import com.dana.proj.proj.dtos.CommentDTO;
import com.dana.proj.proj.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface CommentService {
    void saveComment(CommentDTO commentDto);
}
