package com.example.channelprotest_mvc.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "home_table")
public class Home {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String Description;
    private int priority;
    private int image;

    public  Home(){


    }
    public Home( String title, String description,int image, int priority) {

        this.title = title;
        this.Description = description;
        this.priority = priority;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
