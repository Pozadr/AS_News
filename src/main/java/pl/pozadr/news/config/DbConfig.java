package pl.pozadr.news.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class DbConfig {

    @EventListener(ApplicationReadyEvent.class)
    public void showList() {
        System.out.println("hello");
    }
}
