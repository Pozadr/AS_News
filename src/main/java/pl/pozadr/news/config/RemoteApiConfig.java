package pl.pozadr.news.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.pozadr.news.model.Info;

import java.util.Optional;

@Configuration
public class RemoteApiConfig {
    @Value("${api-token}")
    private String apiToken;

    @Bean
    public Optional<Info> fetchDataFromRemoteApi() {
        RestTemplate restTemplateCity = new RestTemplate();
        String remoteApiUrl = "https://api.currentsapi.services/v1/latest-news?apiKey=" + apiToken;
        return Optional.ofNullable(restTemplateCity.getForObject(remoteApiUrl, Info.class));
    }
}
