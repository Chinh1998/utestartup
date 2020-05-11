package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.Comment;
import com.quangchinh.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(String id) {
        Optional<Comment> cmtOptional= commentRepository.findById(id);
        return cmtOptional.orElse(null);
    }

    @Override
    public List<Comment> getByNewsId(String newsId) {
        return commentRepository.findAllByNewsIdOrderByCreateDateAsc(newsId);
    }

    @Override
    public void deleteCommentByNewsId(String newId) {
        commentRepository.deleteByNewsId(newId);
    }


    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public boolean deleteComment(String id) {
        commentRepository.deleteById(id);
        return true;
    }
}