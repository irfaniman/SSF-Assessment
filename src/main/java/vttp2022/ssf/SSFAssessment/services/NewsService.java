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
import vttp2022.ssf.SSFAssessment.repositories.CryptoRepository;

@Service
public class NewsService {
    
    private static final String URL = "https://min-api.cryptocompare.com/data/v2/news/";
    
    @Value("${CRYPTO_KEY}")
    private String key;
    private String lang = "EN";

    public List<Article> getNews() {

        String payload;

        System.out.printf("Getting news information from cryptoCompare");

        // Creating url with query string
        String url = UriComponentsBuilder.fromUriString(URL)
            .queryParam("lang", lang)
            .queryParam("api_key", key)
            .toUriString();

            // Create the GET request, get URL
            RequestEntity<Void> req = RequestEntity.get(url).build();

            // Make the call to OpenWeatherMap
            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp;

            /* try {
                //Throws an exception if status code not in between 200-399
                resp = template.exchange(req, String.class);
            } catch(Exception ex) {
                System.err.printf("Error: %s\n", ex.getMessage());
                return Collections.emptyList();
            } */
         
            // Get the payload and do something with it
            payload = resp.getBody();
            System.out.println("payload: " + payload);

            Reader strReader = new StringReader(payload);
            // Create a JsonReader from reader
            JsonReader jsonReader = Json.createReader(strReader);
            // Read the payload ad Json Object
            JsonObject weatherResult = jsonReader.readObject();
            JsonArray cities = weatherResult.getJsonArray("weather");
            List<Article> list = new LinkedList<>();
            for (int i = 0; i <cities.size(); i++ ) {
                // weather [0]
                JsonObject jo = cities.getJsonObject(i);
                list.add(Article.create(jo));
            }

    }

    return list;

}




    /* 
    @Autowired
    private CryptoRepository cryptoRepo;

    public List<Crypto> getCrypto(String fsym, String tsyms) {

    // Check if we have weather cached
    Optional<String> opt = cryptoRepo.get(fsym);
    String payload;
    
    System.out.printf(">>>> city: %s\n", city);

    //Check if the box is empty
    if(opt.isEmpty()) {

        System.out.println("Getting weather from OpenWeatherMap");

        // Create url with query string
        String url = UriComponentsBuilder.fromUriString(URL)
            .queryParam("fsym", crypto)
            .queryParam("tsyms", currency)
            .queryParam("")
            .toUriString();

            // Create the GET request, get URL
            RequestEntity<Void> req = RequestEntity.get(url).build();

            // Make the call to OpenWeatherMap
            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp; // from the response body, we are expecting string

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

            cryptoRepo.save(fsym, tsyms, payload);

        } else {
            // Retrieve the value for the box
            payload = opt.get();
            System.out.printf(">>>> cache %s\n", payload);
        }

        // Convert payload to JsonObject
        // Convert String to a Reader
        Reader strReader = new StringReader(payload);
        // Create a JsonReader from reader
        JsonReader jsonReader = Json.createReader(strReader);
        // Read the payload ad Json Object
        JsonObject weatherResult = jsonReader.readObject();
        JsonArray cities = weatherResult.getJsonArray("weather");
        List<Crypto> list = new LinkedList<>();
        for (int i = 0; i <cities.size(); i++ ) {
            // weather [0]
            JsonObject jo = cities.getJsonObject(i);
            list.add(Crypto.create(jo));
        }

        return list;
    } */