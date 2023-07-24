package com.telugucalendar.appsworld.Model;

public class PanchangamTab {
    String id,panchangam_id,title,description;

    public PanchangamTab(String id, String panchangam_id, String title, String description) {
        this.id = id;
        this.panchangam_id = panchangam_id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPanchangam_id() {
        return panchangam_id;
    }

    public void setPanchangam_id(String panchangam_id) {
        this.panchangam_id = panchangam_id;
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

