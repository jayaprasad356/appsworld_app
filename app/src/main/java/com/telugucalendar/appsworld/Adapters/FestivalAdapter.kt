package com.telugucalendar.appsworld.Adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.telugucalendar.appsworld.Model.Festival
import com.telugucalendar.appsworld.R

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*



class FestivalAdapter(val activity: Activity, festivals: ArrayList<Festival>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var festivals: ArrayList<Festival>
    private val ITEMS_BETWEEN_ADS = 6

    init {
        this.festivals = festivals
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.pandugalu_model, parent, false)
        return ExploreItemHolder(view)
    }

    override fun onBindViewHolder(holderParent: RecyclerView.ViewHolder, position: Int) {
        val holder = holderParent as ExploreItemHolder
        val festival: Festival = festivals[position]


        holder.l1.visibility = View.VISIBLE

        val strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000"
        var format = SimpleDateFormat("yyy-MM-dd")
        var newDate: Date? = null
        try {
            newDate = format.parse(festival.date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        format = SimpleDateFormat("dd-MM-yyy")
        val date = format.format(newDate)
        holder.tvFestival.setText(festival.festival)
        holder.tvDate.text = date
//
//        try {
//            if (position == 1 ) {
////                holder.adView.visibility = View.VISIBLE
////                holder.l1.visibility = View.GONE
////                val adRequest = AdRequest.Builder().build()
////                holder.adView.loadAd(adRequest)
//
//            }
//            else if (position == 6 ){
////                holder.adView.visibility = View.VISIBLE
////                holder.l1.visibility = View.GONE
////                val adRequest = AdRequest.Builder().build()
////                holder.adView.loadAd(adRequest)
//
//            }
//
//
//
//
//
//
//            else {
//                holder.l1.visibility = View.VISIBLE
//
//                val strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000"
//                var format = SimpleDateFormat("yyy-MM-dd")
//                var newDate: Date? = null
//                try {
//                    newDate = format.parse(festival.date)
//                } catch (e: ParseException) {
//                    e.printStackTrace()
//                }
//                format = SimpleDateFormat("dd-MM-yyy")
//                val date = format.format(newDate)
//                holder.tvFestival.setText(festival.festival)
//                holder.tvDate.text = date
//            }
//
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }

    }
    override fun getItemCount(): Int {
        return festivals.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    internal class ExploreItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvFestival: TextView
        val tvDate: TextView
        val l1: LinearLayout

        init {
            tvFestival = itemView.findViewById(R.id.tvFestival)
            tvDate = itemView.findViewById(R.id.tvDate)
            l1 = itemView.findViewById(R.id.l1)
        }
    }
}