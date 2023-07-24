package com.telugucalendar.appsworld.Activites

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telugucalendar.appsworld.Adapters.AudioLiveAdapter
import com.telugucalendar.appsworld.R
import com.telugucalendar.appsworld.helper.DatabaseHelper
import com.telugucalendar.appsworld.helper.Session

class BhathiGeethaluActivity : AppCompatActivity() {

    var databaseHelper: DatabaseHelper? = null
    var activity: Activity? = null
    var session: Session? = null
    var recyclerView: RecyclerView? = null
    var audioLiveAdapter: AudioLiveAdapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bhathi_geethalu)

        activity = this@BhathiGeethaluActivity
        session = Session(activity)
        databaseHelper = DatabaseHelper(activity)


        var imgbackbttn = findViewById<ImageView>(R.id.imgbackbttn);

        imgbackbttn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })




        databaseHelper = DatabaseHelper(activity)
        recyclerView = findViewById(R.id.recyclerView)
        // val gridLayoutManager = GridLayoutManager(activity, 2)
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))
        audiolive()
    }


    private fun audiolive() {
        Log.d("AUDIO_COUNT", databaseHelper!!.audiosCount.toString() + "")
        if (databaseHelper!!.audioList.size != 0) {
            audioLiveAdapter = AudioLiveAdapter(activity, databaseHelper!!.audioList)
            recyclerView!!.adapter = audioLiveAdapter
        } else {
            recyclerView!!.visibility = View.GONE
        }
    }
}