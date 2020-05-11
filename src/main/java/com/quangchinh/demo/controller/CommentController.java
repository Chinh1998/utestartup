package com.quangchinh.demo.controller;

import com.quangchinh.demo.dao.Comment;
import com.quangchinh.demo.dao.News;
import com.quangchinh.demo.dao.User;
import com.quangchinh.demo.dto.CommentDTO;
import com.quangchinh.demo.helper.AuthenticationHelper;
import com.quangchinh.demo.service.CommentService;
import com.quangchinh.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final NewsService newsService;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    CommentController(CommentService commentService, NewsService newsService, AuthenticationHelper authenticationHelper) {
        this.commentService = commentService;
        this.newsService = newsService;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<Comment> getAllComment() {
        return commentService.getAll();
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public Comment createComment(@RequestBody CommentDTO commentDTO) {
        User user = authenticationHelper.getLoggedInUser();
        String newId = commentDTO.getNewsId();
        News news = newsService.getById(newId);

        if (user == null && news == null) {
            return  null;
        }else {

            Comment comment = new Comment();
            comment.setContent(commentDTO.getContent());
            comment.setUser(user);
            comment.setNews(news);
            comment.setCreateDate(new Date());

            return commentService.create(comment);
        }
    }

    @GetMapping("/{id}")
    public Comment getComment(@PathVariable String id) {
        return commentService.getById(id);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable String id, @RequestBody Comment comment) {
        comment.setId(id);
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteComment(@PathVariable String id) {
        return commentService.deleteComment(id);
    }
}
