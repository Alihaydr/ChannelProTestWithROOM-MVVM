package com.example.channelprotest_mvc.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "news_table")
public class News {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int image;
    private String description;
    private String site;
    private int priority;


    public News(String title, String description,int image, String site,int priority) {
        this.title = title;
        this.description = description;
        this.site = site;
        this.priority = priority;
        this.image = image;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSite() {
        return site;
    }
    public int getPriority() {
        return priority;
    }
}
