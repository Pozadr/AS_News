package pl.pozadr.news.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.pozadr.news.model.Info;

import java.util.Optional;

@RestController
public class NewsApi {
    @Value("${api-token}")
    private String apiToken;
    Logger logger = LoggerFactory.getLogger(NewsApi.class);

    public Optional<Info> getDataFromRemoteApi() {
        try {
            RestTemplate restTemplateCity = new RestTemplate();
            String remoteApiUrl = "https://api.currentsapi.services/v1/latest-news?apiKey=" + apiToken;
            logger.debug("Starting fetching from remote API.");
            Optional<Info> newsInfo = Optional.ofNullable(restTemplateCity.getForObject(remoteApiUrl, Info.class));
            if (newsInfo.isEmpty()) {
                logger.error("There was an issue during fetching data from remote API. There is no data:" +
                        " \"Info\" fetched.");
            }
            return newsInfo;
        } catch (RestClientException ex) {
            logger.error("Error during fetching from remote API.", ex);
        } finally {
            logger.debug("Completed fetching from remote API.");
        }
        return Optional.empty();
    }
}
