package com.telugucalendar.appsworld.Adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.telugucalendar.appsworld.Model.Sissujanma
import com.telugucalendar.appsworld.R

import kotlin.collections.ArrayList


class SissujunmaAdapter(val activity: Activity, sissujanma: ArrayList<Sissujanma>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var sissujanma: ArrayList<Sissujanma>

    init {
        this.sissujanma = sissujanma
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.sissujanma, parent, false)
        return ExploreItemHolder(view)
    }

    override fun onBindViewHolder(holderParent: RecyclerView.ViewHolder, position: Int) {
        val holder = holderParent as ExploreItemHolder
        val sissujanma: Sissujanma = sissujanma[position]

        holder.tvdescription.setText(sissujanma.description)
        holder.tvTitle.setText(sissujanma.title)
    }

    override fun getItemCount(): Int {
        return sissujanma.size
    }

    internal class ExploreItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvdescription: TextView
        val tvTitle: TextView

        init {
            tvdescription = itemView.findViewById(R.id.tvdescription)
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }
}