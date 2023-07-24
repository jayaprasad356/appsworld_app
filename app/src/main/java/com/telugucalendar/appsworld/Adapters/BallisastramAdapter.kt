package com.telugucalendar.appsworld.Adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.telugucalendar.appsworld.Model.Ballisastram
import com.telugucalendar.appsworld.R
import java.util.*


class BallisastramAdapter(val activity: Activity, ballisastram: ArrayList<Ballisastram>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var ballisastram: ArrayList<Ballisastram>

    init {
        this.ballisastram = ballisastram
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.ballisastram, parent, false)
        return ExploreItemHolder(view)
    }

    override fun onBindViewHolder(holderParent: RecyclerView.ViewHolder, position: Int) {
        val holder = holderParent as ExploreItemHolder
        val ballisastram: Ballisastram = ballisastram[position]

        holder.tvdescription.setVerticalScrollBarEnabled(true)



        holder.tvdescription.loadDataWithBaseURL("file:///android_asset/fonts/", "<html>\n" + "<link rel='stylesheet' type='text/css' href='file:///android_asset/fonts/nats.css'>"+
                "<style>\n" +
                "body {\n" +
                "    font-family: 'nats';\n" +
                "}\n" +
                "\n" +
                "</style>"+
                "<body>\n" +
                ballisastram.description+
                "</body>\n" +
                "</html>"
            , "text/html", "UTF-8", "")
        holder.tvdescription.getSettings().setJavaScriptEnabled(true);
        holder.tvdescription.getSettings().setLoadWithOverviewMode(true);

        holder.tvTitle.setText(ballisastram.title)




    }

    override fun getItemCount(): Int {
        return ballisastram.size
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