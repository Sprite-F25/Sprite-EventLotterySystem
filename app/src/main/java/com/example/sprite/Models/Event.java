package com.example.sprite.Models;

import java.util.Date;

public class Event {
    private String title;
    private String description;
    private Date date;
    private String location;
    private String status;
    private Double price;  // do we really need price though, since we aren't forcing users to pay anything to sign up

    // need images...Bitmap, URI (String) or drawable(int for resource ID)?
    // also need getters and setters for images


    // constructor


    public Event(String title, String description, String location, Date date, Double price) {
        this.location = location;
        this.description = description;
        this.title = title;
        this.price = price;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
