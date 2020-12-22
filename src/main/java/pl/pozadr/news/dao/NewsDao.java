package pl.pozadr.news.dao;

import pl.pozadr.news.dto.NewsDto;
import pl.pozadr.news.model.News;

import java.util.List;

public interface NewsDao {
    List<NewsDto> findAllNews();

    boolean updateNews(News updatedNews);

    News getOneNewsById(Integer id);
}
