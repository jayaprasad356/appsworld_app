package com.telugucalendar.appsworld.Activites

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.telugucalendar.appsworld.R
import com.telugucalendar.appsworld.helper.Constant

class TempleinfoActivity : AppCompatActivity() {
    var activity:Activity = this@TempleinfoActivity
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_templeinfo)

        var imgbackbttn = findViewById<ImageView>(R.id.imgbackbttn);

        imgbackbttn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        var tvTemplename = findViewById<TextView>(R.id.tvTemplename);
        var tvTempleLocation = findViewById<TextView>(R.id.tvTempleLocation);
        var tvTempledescription = findViewById<TextView>(R.id.tvTempledescription);
        var imgtemple = findViewById<ImageView>(R.id.imgtemple);
        var tvTitle = findViewById<TextView>(R.id.tvTitle);

        var name = intent.getStringExtra(Constant.TEMPLEINFO_NAME)
        var location = intent.getStringExtra(Constant.TEMPLEINFO_LOCATION)
        var description = intent.getStringExtra(Constant.TEMPLEINFO_DESCRIPTION)
        var image = intent.getStringExtra(Constant.TEMPLEINFO_IMAGE)


        tvTemplename.setText(name)
        tvTitle.setText(name)
        tvTempleLocation.setText(location)
        tvTempledescription.setText(description)
        Glide.with(activity).load(image).placeholder(R.drawable.temple_img)
            .into(imgtemple)


    }
}


