package pl.pozadr.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pozadr.news.dao.NewsDao;
import pl.pozadr.news.dto.NewsDto;

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

    @RequestMapping(value = "/get-one-news", method = RequestMethod.GET)
    @ResponseBody
    public NewsDto getOneNews(long id) {
        return newsDao.getOneNewsById(id);
    }

    @RequestMapping(value = "/update-news", method = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET})
    public String updateNews(@Validated NewsDto updatedNews) {
        int isModified = newsDao.updateNews(updatedNews);
        if (isModified == 1) {
            return "redirect:/news";
        }
        return "error";
    }
}
