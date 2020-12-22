package pl.pozadr.news.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.pozadr.news.dto.NewsDto;
import pl.pozadr.news.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class NewsDaoImpl implements NewsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<NewsDto> findAllNews() {
        String sql = "SELECT * FROM news";
        List<Map<String, Object>> dbOutput = jdbcTemplate.queryForList(sql);
        List<NewsDto> newsList = new ArrayList<>();
        // DB to POJO
        dbOutput.forEach(element -> newsList.add(new NewsDto(
                String.valueOf(element.get("title")),
                String.valueOf(element.get("image_url")),
                String.valueOf(element.get("description"))
        )));
        for (NewsDto news : newsList) {
            if (news.getImageUrl().equals("None")) {
                news.setImageUrl("https://joemiller.us/wp-content/uploads/news-636978_960_720-1.jpg");
            }
        }
        return newsList;
    }

    @Override
    public boolean updateNews(News updatedNews) {
        return false;
    }

    @Override
    public News getOneNewsById(Integer id) {
        return null;
    }
}
