package com.quangchinh.demo.repository;

import com.quangchinh.demo.dao.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, String> {

    List<News> findNewsByMajorsIdAndApprovedTrue(String majorId);

    List<News> findNewsByUserIdAndApprovedTrue(String userId);

    List<News> findByApprovedTrue();

    List<News> findTop5ByApprovedTrueOrderByViewDesc();

    List<News> findTop5ByApprovedTrueOrderByCreateDateDesc();

    List<News> findNewsByUserIdAndApprovedFalse(String userId);

    List<News> findByApprovedFalse();
}
