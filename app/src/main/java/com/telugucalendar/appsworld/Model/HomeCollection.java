package com.telugucalendar.appsworld.Model;

import java.util.ArrayList;

public class HomeCollection {

    public String date="";
    public String imageName="";
    public String description="";


    public static ArrayList<HomeCollection> date_collection_arr;
    public HomeCollection(String date, String imageName, String description){

        this.date=date;
        this.imageName=imageName;
        this.description= description;

    }
}
