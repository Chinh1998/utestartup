package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.Comment;

import java.util.List;

public interface CommentService {
    Comment create(Comment comment);

    List<Comment> getAll();

    Comment getById(String id);

    List<Comment> getByNewsId(String newsId);

    void deleteCommentByNewsId(String newId);

    Comment updateComment(Comment comment);

    boolean deleteComment(String id);
}
