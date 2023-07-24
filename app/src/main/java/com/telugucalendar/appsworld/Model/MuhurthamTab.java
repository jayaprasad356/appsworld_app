package com.telugucalendar.appsworld.Model;

public class MuhurthamTab {
    String id,muhurtham_id,title,description;

    public MuhurthamTab(String id, String muhurtham_id, String title, String description) {
        this.id = id;
        this.muhurtham_id = muhurtham_id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMuhurtham_id() {
        return muhurtham_id;
    }

    public void setMuhurtham_id(String muhurtham_id) {
        this.muhurtham_id = muhurtham_id;
    }

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
}

