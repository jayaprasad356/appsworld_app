package com.telugucalendar.appsworld.Activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.telugucalendar.appsworld.AddToAudioTask;
import com.telugucalendar.appsworld.AddToFestivalTask;
import com.telugucalendar.appsworld.AddToMuhurthamTabTask;
import com.telugucalendar.appsworld.PanchangamTabTask;
import com.telugucalendar.appsworld.PanchangamTask;
import com.telugucalendar.appsworld.R;
import com.telugucalendar.appsworld.helper.ApiConfig;
import com.telugucalendar.appsworld.helper.Constant;
import com.telugucalendar.appsworld.helper.DatabaseHelper;
import com.telugucalendar.appsworld.helper.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SplashScreenActivity extends AppCompatActivity {
    Activity activity;
    DatabaseHelper databaseHelper;
    Session session;
    TextView tvInstruction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        activity = SplashScreenActivity.this;
        databaseHelper = new DatabaseHelper(activity);
        session=new Session(activity);
        tvInstruction= findViewById(R.id.tvInstruction);

        if (ApiConfig.isConnected(activity)) {
            if(session.getData(Constant.FIREST_TIME).equals("true")){
                getDatalist();
            }else {
                tvInstruction.setVisibility(View.VISIBLE);
                getDataLongData();
            }

        }

    }


    private void getDatalist() {
        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Log.d("response", response);
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.PANCHANGAM_LIST);
//                        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//                        String sql = "INSERT INTO tblpanchangam (pid, date, sunrise, sunset, moonrise, moonset) VALUES (?, ?, ?, ?, ?, ?)";
//                        db.beginTransaction();
//                        SQLiteStatement stmt = db.compileStatement(sql);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
//                                stmt.bindString(1, jsonObject1.getString(Constant.ID));
//                                stmt.bindString(2, jsonObject1.getString(Constant.DATE));
//                                stmt.bindString(3, jsonObject1.getString(Constant.SUNRISE));
//                                stmt.bindString(4, jsonObject1.getString(Constant.SUNSET));
//                                stmt.bindString(5, jsonObject1.getString(Constant.MOONRISE));
//                                stmt.bindString(6, jsonObject1.getString(Constant.MOONSET));
//                                stmt.execute();
//                                stmt.clearBindings();
                                new PanchangamTask(jsonObject1, databaseHelper).execute();

                                // databaseHelper.AddToPanchangam(jsonObject1.getString(Constant.ID), jsonObject1.getString(Constant.DATE), jsonObject1.getString(Constant.SUNRISE), jsonObject1.getString(Constant.SUNSET), jsonObject1.getString(Constant.MOONRISE), jsonObject1.getString(Constant.MOONSET));
                            } else {
                                break;
                            }
                        }
                        System.out.println("++++++++++++++++++++++++++++++++");
//                        db.setTransactionSuccessful();
//                        db.endTransaction();
                        JSONArray jsonArray2 = object.getJSONArray(Constant.PANCHANGAM_TAB_LIST);
//                        SQLiteDatabase pt = databaseHelper.getWritableDatabase();
//                        String sqlpt = "INSERT INTO tblpanchangamtab (ptid, pid, title, description) VALUES (?, ?, ?, ?)";
//                        pt.beginTransaction();
//                        SQLiteStatement stmtpt = pt.compileStatement(sqlpt);
                        for (int i = 0; i < jsonArray2.length(); i++) {
                            JSONObject jsonObject1 = jsonArray2.getJSONObject(i);
                            if (jsonObject1 != null) {
//                                stmtpt.bindString(1, jsonObject1.getString(Constant.ID));
//                                stmtpt.bindString(2, jsonObject1.getString(Constant.PANCHANGAM_ID));
//                                stmtpt.bindString(3, jsonObject1.getString(Constant.TITLE));
//                                stmtpt.bindString(4, jsonObject1.getString(Constant.DESCRIPTION));
//                                stmtpt.execute();
//                                stmtpt.clearBindings();
//                                  databaseHelper.AddToPanchangamTab(jsonObject1.getString(Constant.ID),jsonObject1.getString(Constant.PANCHANGAM_ID),jsonObject1.getString(Constant.TITLE),jsonObject1.getString(Constant.DESCRIPTION));
                                new PanchangamTabTask(jsonObject1, databaseHelper).execute();


                            } else {
                                break;
                            }
                        }
//                        pt.setTransactionSuccessful();
//                        pt.endTransaction();
//                        System.out.println("2++++++++++++++++++++++++++++++++");

                        JSONArray jsonArray3 = object.getJSONArray(Constant.FESTIVALS_LIST);
//                        SQLiteDatabase ft = databaseHelper.getWritableDatabase();
//                        String sqlft = "INSERT INTO tblfestival (fid, date, festival) VALUES (?, ?, ?)";
//                        ft.beginTransaction();
//                        SQLiteStatement stmtft = ft.compileStatement(sqlft);
                        for (int i = 0; i < jsonArray3.length(); i++) {
                            JSONObject jsonObject1 = jsonArray3.getJSONObject(i);
                            if (jsonObject1 != null) {
//                                stmtft.bindString(1, jsonObject1.getString(Constant.ID));
//                                stmtft.bindString(2, jsonObject1.getString(Constant.DATE));
//                                stmtft.bindString(3, jsonObject1.getString(Constant.FESTIVAL));
//                                stmtft.execute();
//                                stmtft.clearBindings();
                                new AddToFestivalTask(jsonObject1, databaseHelper).execute();

                                // databaseHelper.AddToFestival(jsonObject1.getString(Constant.ID),jsonObject1.getString(Constant.DATE),jsonObject1.getString(Constant.FESTIVAL));
                            } else {
                                break;
                            }
                        }
//                        ft.setTransactionSuccessful();
//                        ft.endTransaction();
                        System.out.println("3++++++++++++++++++++++++++++++++");

//                        SQLiteDatabase dba = databaseHelper.getWritableDatabase();
//                        String sqldba = "INSERT INTO tblaudio (id, title, image,lyrics,audio) VALUES (?, ?, ?, ?, ?)";
//                        dba.beginTransaction();
//                        SQLiteStatement stmtdba = dba.compileStatement(sqldba);
                        JSONArray jsonArray14 = object.getJSONArray(Constant.AUDIO_LIST);
                        for (int i = 0; i < jsonArray14.length(); i++) {
                            JSONObject jsonObject1 = jsonArray14.getJSONObject(i);
                            if (jsonObject1 != null) {
//                                stmtdba.bindString(1, jsonObject1.getString(Constant.ID));
//                                stmtdba.bindString(2, jsonObject1.getString(Constant.TITLE));
//                                stmtdba.bindString(3, jsonObject1.getString(Constant.IMAGE));
//                                stmtdba.bindString(4, jsonObject1.getString(Constant.LYRICS));
//                                stmtdba.bindString(5, jsonObject1.getString(Constant.AUDIO));
//                                stmtdba.execute();
//                                stmtdba.clearBindings();
                                new AddToAudioTask(jsonObject1, databaseHelper).execute();

                                // databaseHelper.AddToAudio(jsonObject1.getString(Constant.ID), jsonObject1.getString(Constant.TITLE), jsonObject1.getString(Constant.IMAGE), jsonObject1.getString(Constant.LYRICS), jsonObject1.getString(Constant.AUDIO));
                            } else {
                                break;
                            }
                        }
//                        dba.setTransactionSuccessful();
//                        dba.endTransaction();
//                        SQLiteDatabase dbmt = databaseHelper.getWritableDatabase();
//                        String sqldbmt = "INSERT INTO tblmuhurthamtab (mtid, mid, title,description,date) VALUES (?, ?, ?, ?, ?)";
//                        dbmt.beginTransaction();
//                        SQLiteStatement stmtdbmt = dbmt.compileStatement(sqldbmt);
                        JSONArray jsonArray15 = object.getJSONArray(Constant.MUHURTHAM_TAB_LIST);
                        for (int i = 0; i < jsonArray15.length(); i++) {
                            JSONObject jsonObject1 = jsonArray15.getJSONObject(i);
                            if (jsonObject1 != null) {
//                                stmtdbmt.bindString(1, jsonObject1.getString(Constant.ID));
//                                stmtdbmt.bindString(2, jsonObject1.getString(Constant.MUHURTHAM_ID));
//                                stmtdbmt.bindString(3, jsonObject1.getString(Constant.TITLE));
//                                stmtdbmt.bindString(4, jsonObject1.getString(Constant.DESCRIPTION));
//                                stmtdbmt.bindString(5, jsonObject1.getString(Constant.DATE));
//                                stmtdbmt.execute();
//                                stmtdbmt.clearBindings();
                                new AddToMuhurthamTabTask(jsonObject1, databaseHelper).execute();

                                // databaseHelper.AddToMuhurthamTab(jsonObject1.getString(Constant.ID), jsonObject1.getString(Constant.MUHURTHAM_ID), jsonObject1.getString(Constant.TITLE), jsonObject1.getString(Constant.DESCRIPTION), jsonObject1.getString(Constant.DATE));
                            } else {
                                break;
                            }
                        }

//                        dbmt.setTransactionSuccessful();
//                        dbmt.endTransaction();
                        Intent i = new Intent(activity, HomeActivity.class);
                        startActivity(i);
                        finish();


                    } else {


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, activity, Constant.ALLDATALIST_URL, params, false);


    }
    private void getDataLongData()
    {
        Map<String, String> params = new HashMap<>();
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Log.d("response", response);
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.PANCHANGAM_LIST);
                        SQLiteDatabase db = databaseHelper.getWritableDatabase();
                        String sql = "INSERT INTO tblpanchangam (pid, date, sunrise, sunset, moonrise, moonset) VALUES (?, ?, ?, ?, ?, ?)";
                        db.beginTransaction();
                        SQLiteStatement stmt = db.compileStatement(sql);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                stmt.bindString(1, jsonObject1.getString(Constant.ID));
                                stmt.bindString(2, jsonObject1.getString(Constant.DATE));
                                stmt.bindString(3, jsonObject1.getString(Constant.SUNRISE));
                                stmt.bindString(4, jsonObject1.getString(Constant.SUNSET));
                                stmt.bindString(5, jsonObject1.getString(Constant.MOONRISE));
                                stmt.bindString(6, jsonObject1.getString(Constant.MOONSET));
                                stmt.execute();
                                stmt.clearBindings();

                            } else {
                                break;
                            }
                        }
                        db.setTransactionSuccessful();
                        db.endTransaction();
                        JSONArray jsonArray2 = object.getJSONArray(Constant.PANCHANGAM_TAB_LIST);
                        SQLiteDatabase pt = databaseHelper.getWritableDatabase();
                        String sqlpt = "INSERT INTO tblpanchangamtab (ptid, pid, title, description) VALUES (?, ?, ?, ?)";
                        pt.beginTransaction();
                        SQLiteStatement stmtpt = pt.compileStatement(sqlpt);
                        for (int i = 0; i < jsonArray2.length(); i++) {
                            JSONObject jsonObject1 = jsonArray2.getJSONObject(i);
                            if (jsonObject1 != null) {
                                stmtpt.bindString(1, jsonObject1.getString(Constant.ID));
                                stmtpt.bindString(2, jsonObject1.getString(Constant.PANCHANGAM_ID));
                                stmtpt.bindString(3, jsonObject1.getString(Constant.TITLE));
                                stmtpt.bindString(4, jsonObject1.getString(Constant.DESCRIPTION));
                                stmtpt.execute();
                                stmtpt.clearBindings();
                            } else {
                                break;
                            }
                        }
                        pt.setTransactionSuccessful();
                        pt.endTransaction();

                        JSONArray jsonArray3 = object.getJSONArray(Constant.FESTIVALS_LIST);
                        SQLiteDatabase ft = databaseHelper.getWritableDatabase();
                        String sqlft = "INSERT INTO tblfestival (fid, date, festival) VALUES (?, ?, ?)";
                        ft.beginTransaction();
                        SQLiteStatement stmtft = ft.compileStatement(sqlft);
                        for (int i = 0; i < jsonArray3.length(); i++) {
                            JSONObject jsonObject1 = jsonArray3.getJSONObject(i);
                            if (jsonObject1 != null) {
                                stmtft.bindString(1, jsonObject1.getString(Constant.ID));
                                stmtft.bindString(2, jsonObject1.getString(Constant.DATE));
                                stmtft.bindString(3, jsonObject1.getString(Constant.FESTIVAL));
                                stmtft.execute();
                                stmtft.clearBindings();

                            } else {
                                break;
                            }
                        }
                        ft.setTransactionSuccessful();
                        ft.endTransaction();

                        SQLiteDatabase dba = databaseHelper.getWritableDatabase();
                        String sqldba = "INSERT INTO tblaudio (id, title, image,lyrics,audio) VALUES (?, ?, ?, ?, ?)";
                        dba.beginTransaction();
                        SQLiteStatement stmtdba = dba.compileStatement(sqldba);
                        JSONArray jsonArray14 = object.getJSONArray(Constant.AUDIO_LIST);
                        for (int i = 0; i < jsonArray14.length(); i++) {
                            JSONObject jsonObject1 = jsonArray14.getJSONObject(i);
                            if (jsonObject1 != null) {
                                stmtdba.bindString(1, jsonObject1.getString(Constant.ID));
                                stmtdba.bindString(2, jsonObject1.getString(Constant.TITLE));
                                stmtdba.bindString(3, jsonObject1.getString(Constant.IMAGE));
                                stmtdba.bindString(4, jsonObject1.getString(Constant.LYRICS));
                                stmtdba.bindString(5, jsonObject1.getString(Constant.AUDIO));
                                stmtdba.execute();
                                stmtdba.clearBindings();
                            } else {
                                break;
                            }
                        }
                        dba.setTransactionSuccessful();
                        dba.endTransaction();
                        SQLiteDatabase dbmt = databaseHelper.getWritableDatabase();
                        String sqldbmt = "INSERT INTO tblmuhurthamtab (mtid, mid, title,description,date) VALUES (?, ?, ?, ?, ?)";
                        dbmt.beginTransaction();
                        SQLiteStatement stmtdbmt = dbmt.compileStatement(sqldbmt);
                        JSONArray jsonArray15 = object.getJSONArray(Constant.MUHURTHAM_TAB_LIST);
                        for (int i = 0; i < jsonArray15.length(); i++) {
                            JSONObject jsonObject1 = jsonArray15.getJSONObject(i);
                            if (jsonObject1 != null) {
                                stmtdbmt.bindString(1, jsonObject1.getString(Constant.ID));
                                stmtdbmt.bindString(2, jsonObject1.getString(Constant.MUHURTHAM_ID));
                                stmtdbmt.bindString(3, jsonObject1.getString(Constant.TITLE));
                                stmtdbmt.bindString(4, jsonObject1.getString(Constant.DESCRIPTION));
                                stmtdbmt.bindString(5, jsonObject1.getString(Constant.DATE));
                                stmtdbmt.execute();
                                stmtdbmt.clearBindings();
                            } else {
                                break;
                            }
                        }

                        dbmt.setTransactionSuccessful();
                        dbmt.endTransaction();
                        session.setData(Constant.FIREST_TIME,"true");
                        Intent i = new Intent(activity, HomeActivity.class);
                        startActivity(i);
                        finish();


                    } else {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, activity, Constant.ALLDATALIST_URL, params,false);

    }
}