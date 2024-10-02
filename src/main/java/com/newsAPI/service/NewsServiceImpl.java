package com.newsAPI.service;

import com.newsAPI.config.NewsApiConfig;
import com.newsAPI.constants.Constants;
import com.newsAPI.dto.NewsDto;
import com.newsAPI.model.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NewsApiConfig newsApiConfig;


    @Override
    public List<NewsDto> getNewsArticles() {

        String finalUrl = String.format(Constants.API_URL,newsApiConfig.getApiUrl(),newsApiConfig.getApiKey());

        //fetch articles
        NewsResponse response = restTemplate.getForObject(finalUrl, NewsResponse.class);

        //Transform into DTOs
        return response.getArticles().stream().map(article -> {
            NewsDto dto = new NewsDto();
            dto.setTitle(article.getTitle());
            dto.setDescription(article.getDescription());
            dto.setUrl(article.getUrl());
            dto.setUrlToImage(article.getUrlToImage());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public NewsDto getNewsArticleByUrl(String url) {
        return null;
    }
}
