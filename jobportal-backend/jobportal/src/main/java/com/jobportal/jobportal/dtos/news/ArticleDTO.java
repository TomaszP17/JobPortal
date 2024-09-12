package com.jobportal.jobportal.dtos.news;

import lombok.Data;

@Data
public class ArticleDTO {

    private String title;

    private String description;

    private String url;

    private String publishedAt;

}
