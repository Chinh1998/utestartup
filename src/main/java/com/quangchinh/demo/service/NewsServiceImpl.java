package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.News;
import com.quangchinh.demo.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News create(News news) {
        return newsRepository.save(news);
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getAllShortenedContent() {
        List<News> allNews = getAllApproved();
        return allNews.stream().peek(this::shortenNews).collect(Collectors.toList());
    }

    @Override
    public List<News> getNewsByMajorsId(String id) {
        List<News> majorNews = newsRepository.findNewsByMajorsIdAndApprovedTrue(id);
        return majorNews.stream().peek(this::shortenNews).collect(Collectors.toList());
    }

    @Override
    public List<News> getNewsByUserId(String userId) {
        List<News> myNews = newsRepository.findNewsByUserIdAndApprovedTrue(userId);
        return myNews.stream().peek(this::shortenNews).collect(Collectors.toList());
    }

    @Override
    public List<News> get5RecentNews() {
        return newsRepository.findTop5ByApprovedTrueOrderByCreateDateDesc();
    }

    @Override
    public News getById(String id) {
        Optional<News> newsOptional = newsRepository.findById(id);
        return newsOptional.orElse(null);
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News approveNews(String newsId) {
        News news = getById(newsId);
        news.setApproved(true);
        return updateNews(news);
    }

    @Override
    public boolean deleteNews(String id) {
        newsRepository.deleteById(id);
        return true;
    }

    @Override
    public List<News> get5MostView() {
        return newsRepository.findTop5ByApprovedTrueOrderByViewDesc();
    }

    @Override
    public List<News> getPendingPostByUserId(String userId) {
        List<News> myPendingPost = newsRepository.findNewsByUserIdAndApprovedFalse(userId);
        return myPendingPost.stream().peek(this::shortenNews).collect(Collectors.toList());
    }

    @Override
    public List<News> getPendingNews() {
        return newsRepository.findByApprovedFalse()
                .stream().peek(this::shortenNews).collect(Collectors.toList());
    }

    private List<News> getAllApproved() {
        return newsRepository.findByApprovedTrue();
    }

    private void shortenNews(News news) {
        String shortenedContent = news.getContent().substring(0, 150) + "...";
        news.setContent(shortenedContent);
    }
}
