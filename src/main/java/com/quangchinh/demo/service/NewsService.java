package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.News;

import java.util.List;

public interface NewsService {

    News create(News news);

    List<News> getAll();

    List<News> getAllShortenedContent();

    List<News> getNewsByMajorsId(String id);

    List<News> getNewsByUserId(String userId);

    List<News> get5RecentNews();

    News getById(String id);

    News updateNews(News news);

    News approveNews(String newsId);

    boolean deleteNews(String id);

    List<News> get5MostView();

    List<News> getPendingPostByUserId(String id);

    List<News> getPendingNews();
}
