package com.telugucalendar.appsworld.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.telugucalendar.appsworld.Model.PandugaluModel
import com.telugucalendar.appsworld.databinding.PandugaluModelBinding

 class PandugaluAdapter(private val pandugaluArrayList : ArrayList<PandugaluModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var binding : PandugaluModelBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = PandugaluModelBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).apply {
            this.bind(pandugaluArrayList[position])
        }
    }

    override fun getItemCount(): Int = pandugaluArrayList.size

     //TODO: FIX BINDING function
   inner class ItemViewHolder(private val itemView: PandugaluModelBinding)
        : RecyclerView.ViewHolder(itemView.root) {
             fun bind(arrayList: PandugaluModel) {



            }
    }
}