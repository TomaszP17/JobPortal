package com.jobportal.jobportal.controllers

import com.jobportal.jobportal.dtos.news.ArticleDTO
import com.jobportal.jobportal.dtos.news.NewsResponseDTO
import com.jobportal.jobportal.services.news.NewsService
import reactor.core.publisher.Mono
import spock.lang.Specification

class NewsControllerTest extends Specification {

    def newsService = Mock(NewsService)
    def controller = new NewsController(newsService)

    def "should return news with articles when valid query is provided"() {
        given: "mocked service response with news and articles"
        String query = "Java"
        def article = new ArticleDTO(title: "Sample Title", description: "Sample Description", url: "https://example.com", publishedAt: "2024-09-01T12:00:00Z")
        def newsResponse = new NewsResponseDTO(status: "ok", totalResults: 1, articles: [article])
        newsService.fetchNews(query) >> Mono.just(newsResponse)

        when: "the getNews method is called with a valid query"
        def result = controller.getNews(query).block()

        then: "the result is not null and contains a valid news response"
        result != null
        result.getStatus() == "ok"
        result.getTotalResults() == 1
        result.getArticles().size() == 1
        def returnedArticle = result.getArticles().get(0)
        returnedArticle.getTitle() == "Sample Title"
        returnedArticle.getDescription() == "Sample Description"
        returnedArticle.getUrl() == "https://example.com"
        returnedArticle.getPublishedAt() == "2024-09-01T12:00:00Z"
    }

    def "should handle empty articles when no articles are found"() {
        given: "mocked service response with no articles"
        String query = "EmptyQuery"
        def newsResponse = new NewsResponseDTO(status: "ok", totalResults: 0, articles: [])
        newsService.fetchNews(query) >> Mono.just(newsResponse)

        when: "the getNews method is called"
        def result = controller.getNews(query).block()

        then: "the result contains no articles"
        result != null
        result.getStatus() == "ok"
        result.getTotalResults() == 0
        result.getArticles().isEmpty()
    }
}
