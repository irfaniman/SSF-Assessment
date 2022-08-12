package vttp2022.ssf.SSFAssessment.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.ssf.SSFAssessment.models.Article;
import vttp2022.ssf.SSFAssessment.repositories.NewsRepository;

@Service
public class NewsService {

    @Autowired 
    private NewsRepository newsRepo;
    private static final String URL = "https://min-api.cryptocompare.com/data/v2/news/";
        
    @Value("${CRYPTO_KEY}")
    private String key;
    private String lang = "EN";

    public List<Article> getArticles() {

        String payload;

        System.out.println("Getting news information from cryptoCompare");

        // Creating url with query string
        String url = UriComponentsBuilder.fromUriString(URL)
            .queryParam("lang", lang)
            .queryParam("api_key", key)
            .toUriString();

            // Create the GET request, get URL
            RequestEntity<Void> req = RequestEntity.get(url).build();

            // Make the call to cryptoCompare
            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp;
    
            try {
                //Throws an exception if status code not in between 200-399
                resp = template.exchange(req, String.class);
            } catch(Exception ex) {
                System.err.printf("Error: %s\n", ex.getMessage());
                return Collections.emptyList();
            } 
         
            // Get the payload and do something with it
            payload = resp.getBody();
            System.out.println("payload: " + payload);

            Reader strReader = new StringReader(payload);
            // Create a JsonReader from reader
            JsonReader jsonReader = Json.createReader(strReader);
            // Read the payload as Json Object
            JsonObject newsResult = jsonReader.readObject();
            JsonArray data = newsResult.getJsonArray("Data");
            List<Article> list = new LinkedList<>();
            for (int i = 0; i <data.size(); i++ ) {
                JsonObject jo = data.getJsonObject(i);
                list.add(Article.create(jo));
                
            }

        return list;
        
    }

    public Optional<Article> getNewsById(String id) {
        return null;
    }

}
