package pl.pozadr.news.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.pozadr.news.api.NewsApi;
import pl.pozadr.news.model.Info;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
public class DbConfig {

    private final DataSource dataSource;
    private final NewsApi newsApi;

    @Autowired
    public DbConfig(DataSource dataSource, NewsApi newsApi) {
        this.dataSource = dataSource;
        this.newsApi = newsApi;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void initDb() {
        String dropTable = "DROP TABLE IF EXISTS `news`";
        getJdbcTemplate().update(dropTable);

        String createTable = "CREATE TABLE news (news_id int AUTO_INCREMENT PRIMARY KEY, " +
                "title varchar (1000), image_url varchar (1000), description varchar (10000))";
        getJdbcTemplate().update(createTable);

        String sql = "INSERT INTO news (title, image_url, description) VALUES (?,?,?)";

        Optional<Info> infoOptional = newsApi.getDataFromRemoteApi();
        infoOptional.ifPresent(info -> info.getNews()
                .forEach(news -> getJdbcTemplate().update(sql, news.getTitle(), news.getImage(),
                        news.getDescription())));
    }

}
