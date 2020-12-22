package pl.pozadr.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.pozadr.news.dao.NewsDao;

@org.springframework.stereotype.Controller
public class Controller {
    private final NewsDao newsDao;

    @Autowired
    public Controller(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNews(Model model) {
        model.addAttribute("allNews", newsDao.findAllNews());
        return "news";
    }
}
