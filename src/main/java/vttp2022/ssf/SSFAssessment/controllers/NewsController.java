package vttp2022.ssf.SSFAssessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.SSFAssessment.models.Article;
import vttp2022.ssf.SSFAssessment.services.NewsService;

@Controller
@RequestMapping(path = "/")
public class NewsController {

    @Autowired
    private NewsService cryptoSvc;

    @GetMapping
    public String getNews(Model model) {

        List<Article> news = cryptoSvc.getNews();
        model.addAttribute("id", id);
        model.addAttribute("tsyms", published_on);
        model.addAttribute("fsym", fsym);
        model.addAttribute("tsyms", tsyms);
        model.addAttribute("fsym", fsym);
        model.addAttribute("tsyms", tsyms);
        model.addAttribute("fsym", fsym);
        model.addAttribute("tsyms", tsyms);

        return "index";
    }

}

