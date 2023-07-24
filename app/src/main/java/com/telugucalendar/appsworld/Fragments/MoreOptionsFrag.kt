package com.telugucalendar.appsworld.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.telugucalendar.appsworld.Activites.BhathiGeethaluActivity
import com.telugucalendar.appsworld.Activites.HomeActivity
import com.telugucalendar.appsworld.Activites.TempleListActivity
import com.telugucalendar.appsworld.R
import com.telugucalendar.appsworld.databinding.FragmentMoreOptionsBinding

class MoreOptionsFrag : Fragment() {

    private lateinit var binding : FragmentMoreOptionsBinding
    // This is the part of BottomNavigation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if(HomeActivity.navbar!!.visibility == View.GONE) {
            HomeActivity.navbar!!.visibility = View.VISIBLE
        }
        // Inflate the layout for this fragment
        binding = FragmentMoreOptionsBinding.inflate(layoutInflater,container,false)
        binding.cvBalliSastram.setOnClickListener {
            HomeActivity.fm?.beginTransaction()?.replace(R.id.Container,BalliSastramFragment())
                ?.addToBackStack("null")?.commit()
        }
        binding.CvNamaKaranam.setOnClickListener {
            HomeActivity.fm?.beginTransaction()?.replace(R.id.Container,NamaKaranamFrag())
                ?.addToBackStack("null")?.commit()
        }
        binding.CvPadhaDosa.setOnClickListener {
            HomeActivity.fm?.beginTransaction()?.replace(R.id.Container,SissuJanmaFragment())
                ?.addToBackStack("null")?.commit()
        }
        binding.CvMoreApps.setOnClickListener {
            val url = "https://play.google.com/store/apps/dev?id=5677441121992087281"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        binding.CvTempleinfo.setOnClickListener {
            val intent = Intent(activity, TempleListActivity::class.java)
            startActivity(intent)

        }
        binding.CvBhakthi.setOnClickListener {
            val intent = Intent(activity, BhathiGeethaluActivity::class.java)
            startActivity(intent)

        }
        return binding.root
    }
}