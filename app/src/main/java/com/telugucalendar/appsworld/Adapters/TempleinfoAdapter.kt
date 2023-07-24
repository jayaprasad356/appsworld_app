package com.telugucalendar.appsworld.Adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.telugucalendar.appsworld.Activites.TempleinfoActivity
import com.telugucalendar.appsworld.Model.Templeinfo
import com.telugucalendar.appsworld.R
import com.telugucalendar.appsworld.helper.Constant
import java.util.*


class TempleinfoAdapter(val activity: Activity, templeinfo: ArrayList<Templeinfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var templeinfo: ArrayList<Templeinfo>

    init {
        this.templeinfo = templeinfo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(activity).inflate(R.layout.templelist_layout, parent, false)
        return ExploreItemHolder(view)
    }

    override fun onBindViewHolder(holderParent: RecyclerView.ViewHolder, position: Int) {
        val holder = holderParent as ExploreItemHolder
        val templeinfo: Templeinfo = templeinfo[position]



        holder.tvTemplename.setText(templeinfo.name)
        holder.tvTempleLocation.setText(templeinfo.location)
        Glide.with(activity).load(templeinfo.image).placeholder(R.drawable.four_img)
            .into(holder.imgtemple)

        holder.itemView.setOnClickListener(View.OnClickListener {

            val intent = Intent(activity, TempleinfoActivity::class.java)
            intent.putExtra(Constant.TEMPLEINFO_ID, templeinfo.id)
            intent.putExtra(Constant.TEMPLEINFO_NAME, templeinfo.name)
            intent.putExtra(Constant.TEMPLEINFO_LOCATION, templeinfo.location)
            intent.putExtra(Constant.TEMPLEINFO_DESCRIPTION, templeinfo.description)
            intent.putExtra(Constant.TEMPLEINFO_IMAGE, templeinfo.image)
            activity.startActivity(intent)

        })



    }

    override fun getItemCount(): Int {
        return templeinfo.size
    }

    internal class ExploreItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imgtemple: ImageView
        val tvTemplename: TextView
        val tvTempleLocation: TextView

        init {
            imgtemple = itemView.findViewById(R.id.imgtemple)
            tvTempleLocation = itemView.findViewById(R.id.tvTempleLocation)
            tvTemplename = itemView.findViewById(R.id.tvTemplename)
        }
    }
}