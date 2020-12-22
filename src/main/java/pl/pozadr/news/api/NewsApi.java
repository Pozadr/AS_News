package pl.pozadr.news.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.pozadr.news.model.Info;
import pl.pozadr.news.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class NewsApi {
    @Value("${api-token}")
    private String apiToken;

    public List<News> getDataFromRemoteApi() {
        RestTemplate restTemplateCity = new RestTemplate();
        String remoteApiUrl = "https://api.currentsapi.services/v1/latest-news?apiKey=" + apiToken;
        Optional<Info> newsInfo = Optional.ofNullable(restTemplateCity.getForObject(remoteApiUrl, Info.class));
        if (newsInfo.isPresent()) {
            return newsInfo.get().getNews();
        }
        return new ArrayList<>();
    }
}
