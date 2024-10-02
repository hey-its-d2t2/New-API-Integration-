package com.newsAPI.controller;

import com.newsAPI.dto.NewsDto;
import com.newsAPI.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService newService;

    @GetMapping("/news")
    public String showNews(Model model) {
        List<NewsDto> articles = newService.getNewsArticles();
        model.addAttribute("articles", articles);
         return "news";
    }

  /*  @GetMapping("/news/{url:.+}")
    public String viewFullArticle(@PathVariable String url, Model model) {
        // Decode the URL to retrieve the article correctly
        String decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8);
        NewsDto article = newService.getNewsArticleByUrl(decodedUrl);
        model.addAttribute("article", article);
        return "full-news";
    }*/
}
