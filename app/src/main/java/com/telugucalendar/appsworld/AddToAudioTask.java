package com.telugucalendar.appsworld;

import android.os.AsyncTask;

import com.telugucalendar.appsworld.helper.Constant;
import com.telugucalendar.appsworld.helper.DatabaseHelper;

import org.json.JSONException;
import org.json.JSONObject;


    public class AddToAudioTask extends AsyncTask<Void, Void, Void> {
        private JSONObject jsonObject1;
        private DatabaseHelper databaseHelper;

        public AddToAudioTask(JSONObject jsonObject1, DatabaseHelper databaseHelper) {
            this.jsonObject1 = jsonObject1;
            this.databaseHelper=databaseHelper;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                databaseHelper.AddToAudio(
                        jsonObject1.getString(Constant.ID),
                        jsonObject1.getString(Constant.TITLE),
                        jsonObject1.getString(Constant.IMAGE),
                        jsonObject1.getString(Constant.LYRICS),
                        jsonObject1.getString(Constant.AUDIO));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

