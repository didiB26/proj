package com.dana.proj.proj.repository;

import com.dana.proj.proj.model.ImageComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageCommentRepository extends JpaRepository<ImageComment, Long> {
}
