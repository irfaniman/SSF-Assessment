package vttp2022.ssf.SSFAssessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2022.ssf.SSFAssessment.models.Article;
import vttp2022.ssf.SSFAssessment.services.NewsService;

@Controller
@RequestMapping(path = "/")
public class NewsController {

    @Autowired
    private NewsService newsSvc;

    @GetMapping
    public String getNews(Model model) {

        List<Article> news = newsSvc.getArticles();
        model.addAttribute("news", news);
        return "index";
    }

}

