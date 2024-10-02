package com.newsAPI.service;

import com.newsAPI.dto.NewsDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> getNewsArticles();
    /*NewsDto getNewsArticleByUrl(String url);*/
}
