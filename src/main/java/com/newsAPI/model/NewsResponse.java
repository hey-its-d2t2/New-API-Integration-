package com.newsAPI.model;

import com.newsAPI.dto.NewsDto;
import lombok.Data;

import java.util.List;

@Data
public class NewsResponse {

    private String status;
    private int totalResults;
    private List<NewsDto> articles;
}
