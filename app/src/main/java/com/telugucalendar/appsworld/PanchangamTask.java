package com.telugucalendar.appsworld;

import android.os.AsyncTask;

import com.telugucalendar.appsworld.helper.Constant;
import com.telugucalendar.appsworld.helper.DatabaseHelper;

import org.json.JSONException;
import org.json.JSONObject;


public class PanchangamTask extends AsyncTask<Void, Void, Void> {
    private JSONObject jsonObject1;
    private DatabaseHelper databaseHelper;

    public PanchangamTask(JSONObject jsonObject1, DatabaseHelper databaseHelper) {
        this.jsonObject1 = jsonObject1;
        this.databaseHelper = databaseHelper;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            databaseHelper.AddToPanchangam(jsonObject1.getString(Constant.ID),
                    jsonObject1.getString(Constant.DATE),
                    jsonObject1.getString(Constant.SUNRISE),
                    jsonObject1.getString(Constant.SUNSET),
                    jsonObject1.getString(Constant.MOONRISE),
                    jsonObject1.getString(Constant.MOONSET));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

