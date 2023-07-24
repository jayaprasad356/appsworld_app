package com.telugucalendar.appsworld.Adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.telugucalendar.appsworld.Model.Namakaranam
import com.telugucalendar.appsworld.R

import kotlin.collections.ArrayList


class NamakaranamAdapter(val activity: Activity, namakaranam: ArrayList<Namakaranam>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var namakaranam: ArrayList<Namakaranam>

    init {
        this.namakaranam = namakaranam
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.namakaranam, parent, false)
        return ExploreItemHolder(view)
    }

    override fun onBindViewHolder(holderParent: RecyclerView.ViewHolder, position: Int) {
        val holder = holderParent as ExploreItemHolder
        val namakaranam: Namakaranam = namakaranam[position]

        holder.tvdescription.setVerticalScrollBarEnabled(true)

        holder.tvdescription.loadDataWithBaseURL("file:///android_asset/fonts/", "<html>\n" + "<link rel='stylesheet' type='text/css' href='file:///android_asset/fonts/nats.css'>"+
                "<style>\n" +
                "body {\n" +
                "    font-family: 'nats';\n" +
                "}\n" +
                "\n" +
                "</style>"+
                "<body>\n" +
                namakaranam.description+
                "</body>\n" +
                "</html>"
            , "text/html", "UTF-8", "")
        holder.tvdescription.getSettings().setJavaScriptEnabled(true);
        holder.tvdescription.getSettings().setLoadWithOverviewMode(true);
        holder.tvTitle.setText(namakaranam.title)

    }

    override fun getItemCount(): Int {
        return namakaranam.size
    }

    internal class ExploreItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvdescription: WebView
        val tvTitle: TextView

        init {
            tvdescription = itemView.findViewById(R.id.tvdescription)
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }
}