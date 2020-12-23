package pl.pozadr.news.dao;

import pl.pozadr.news.dto.NewsDto;

import java.util.List;

public interface NewsDao {
    List<NewsDto> findAllNews();

    int updateNews(NewsDto updatedNews);

    NewsDto getOneNewsById(long id);
}
