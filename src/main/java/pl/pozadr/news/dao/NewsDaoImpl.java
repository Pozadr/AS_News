package pl.pozadr.news.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.pozadr.news.dto.NewsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                Long.parseLong(String.valueOf(element.get("news_id"))),
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
    public int updateNews(NewsDto updatedNews) {
        String sql = "UPDATE news SET news.title = ?, news.image_url = ?, news.description = ? WHERE news_id = ?";
        return jdbcTemplate.update(sql, updatedNews.getTitle(), updatedNews.getImageUrl(),
                updatedNews.getDescription(), updatedNews.getId());
    }

    @Override
    public NewsDto getOneNewsById(long id) {
        String sql = "SELECT * FROM news WHERE news_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> new NewsDto(
                            rs.getLong("news_id"),
                            rs.getString("title"),
                            rs.getString("image_url"),
                            rs.getString("description")),
                    id);
        } catch (IncorrectResultSizeDataAccessException ex) {
            System.out.println(ex.getMessage());
            return new NewsDto();
        }
    }

}
