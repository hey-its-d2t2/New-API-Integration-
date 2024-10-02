package com.newsAPI.service;

import com.newsAPI.config.NewsApiConfig;
import com.newsAPI.constants.Constants;
import com.newsAPI.dto.NewsDto;
import com.newsAPI.dto.Source;
import com.newsAPI.model.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NewsApiConfig newsApiConfig;


    @Override
    public List<NewsDto> getNewsArticles() {

        String finalUrl = String.format(Constants.API_URL, newsApiConfig.getApiUrl(), newsApiConfig.getApiKey());

        //fetch articles
        NewsResponse response = restTemplate.getForObject(finalUrl, NewsResponse.class);

        //Transform into DTOs
        return response.getArticles().stream().map(article -> {
            NewsDto dto = new NewsDto();
            dto.setTitle(article.getTitle());
            dto.setDescription(article.getDescription());
            dto.setUrl(article.getUrl());
            dto.setUrlToImage(article.getUrlToImage());
            dto.setPublishedAt(article.getPublishedAt());
            return dto;
        }).collect(Collectors.toList());

    }

   /* @Override
    public NewsDto getNewsArticleByUrl(String url) {
        try {
            // Fetch the article by URL
            NewsDto article = restTemplate.getForObject(url, NewsDto.class);

            // Map the response to NewsDto
            NewsDto dto = new NewsDto();
            Optional.ofNullable(article).ifPresent(a -> {
                dto.setTitle(a.getTitle());
                dto.setDescription(a.getDescription());
                dto.setUrl(a.getUrl());
                dto.setUrlToImage(a.getUrlToImage());
                dto.setAuthor(a.getAuthor());
                dto.setPublishedAt(a.getPublishedAt());
                dto.setContent(a.getContent());

                // Handle nested Source object
                if (a.getSource() != null) {
                    Source source = new Source();
                    source.setId(a.getSource().getId());
                    source.setName(a.getSource().getName());
                    dto.setSource(source);
                }
            });

            return dto;

        } catch (HttpClientErrorException e) {
            // Log error and return an empty NewsDto or handle the error appropriately
            System.err.println("Error fetching article: " + e.getMessage());
            return new NewsDto();  // Return empty NewsDto if there's an error
        }
    }*/
}
