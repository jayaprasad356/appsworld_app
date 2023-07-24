package com.telugucalendar.appsworld.Model;

public class PoojaluTab {
    String id,poojalu_id,subcategory_id,title,description,sub_title,sub_description;

    public PoojaluTab(String id, String poojalu_id, String subcategory_id, String title, String description, String sub_title, String sub_description) {
        this.id = id;
        this.poojalu_id = poojalu_id;
        this.subcategory_id = subcategory_id;
        this.title = title;
        this.description = description;
        this.sub_title = sub_title;
        this.sub_description = sub_description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoojalu_id() {
        return poojalu_id;
    }

    public void setPoojalu_id(String poojalu_id) {
        this.poojalu_id = poojalu_id;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
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

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getSub_description() {
        return sub_description;
    }

    public void setSub_description(String sub_description) {
        this.sub_description = sub_description;
    }
}

