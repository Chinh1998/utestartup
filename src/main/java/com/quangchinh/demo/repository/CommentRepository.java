package com.quangchinh.demo.repository;

import com.quangchinh.demo.dao.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findAllByNewsIdOrderByCreateDateAsc(String newId);

    @Transactional
    void deleteByNewsId(String newsId);
}
