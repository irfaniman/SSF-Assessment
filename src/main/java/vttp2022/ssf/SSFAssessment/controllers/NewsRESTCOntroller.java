package vttp2022.ssf.SSFAssessment.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2022.ssf.SSFAssessment.models.Article;
import vttp2022.ssf.SSFAssessment.services.NewsService;

@RestController
@RequestMapping(path = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTCOntroller {

    @Autowired
    private NewsService newsSvc;

    @PostMapping(value = "{id}")
    public ResponseEntity<String> getNews(@PathVariable String id) {
        Optional<Article> opt = newsSvc.getNewsById(id);

        if (opt.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                .add("error", "Id %s not found".formatted(id))
                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(err.toString());
        }
    
        Article article = opt.get();
        return ResponseEntity.ok(article.toJson().toString());
    }

} 