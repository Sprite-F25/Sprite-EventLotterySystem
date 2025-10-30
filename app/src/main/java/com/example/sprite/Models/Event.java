package com.example.sprite.Models;

import java.util.Date;

public class Event {
    private String title;
    private String description;
    private Date date;
    private String location;

    // need images...Bitmap, URI (String) or drawable(int for resource ID)?
    // also need getters and setters for images


    // constructor
    public Event(String title, String description, String location, Date date) {
        this.location = location;
        this.description = description;
        this.title = title;
        this.date = date;
    }

    // getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
