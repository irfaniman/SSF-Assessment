package vttp2022.ssf.SSFAssessment.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Article {
    
    private String id ;
    private Integer published_on;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getPublished_on() { return published_on; }
    public void setPublished_on(Integer published_on) { this.published_on = published_on; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    
    public String getImageurl() { return imageurl; }
    public void setImageurl(String imageurl) { this.imageurl = imageurl; }
    
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
    
    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }
    
    // Json to model
    public static Article create(JsonObject jo) {
        Article a = new Article();
        a.setId(jo.getString("id"));
        a.setPublished_on(jo.getInt("published_on"));
        a.setTitle(jo.getString("title"));
        a.setUrl(jo.getString("url"));
        a.setImageurl(jo.getString("imageUrl"));
        a.setBody(jo.getString("body"));
        a.setTags(jo.getString("tags"));
        a.setCategories(jo.getString("categories"));
        return a;
    }
    // model to Json
    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("id", id)
        .add("published_on", published_on)
        .add("title", title)
        .add("url", url)
        .add("imageurl", imageurl)
        .add("body", body)
        .add("tags", tags)
        .add("categories", categories)
        .build();
        }

    public static Article create(String jsonStr) {
        StringReader strReader = new StringReader(jsonStr);
        JsonReader reader = Json.createReader(strReader);
        return create(reader.readObject());

    }

}
