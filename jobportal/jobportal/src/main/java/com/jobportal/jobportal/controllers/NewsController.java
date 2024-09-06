package com.jobportal.jobportal.controllers;

import com.jobportal.jobportal.dtos.news.NewsResponseDTO;
import com.jobportal.jobportal.services.news.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/api/news")
    public Mono<NewsResponseDTO> getNews(@RequestParam String query) {
        return newsService.fetchNews(query);
    }
}
