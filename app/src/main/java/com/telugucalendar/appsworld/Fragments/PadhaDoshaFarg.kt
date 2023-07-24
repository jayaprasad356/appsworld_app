package com.telugucalendar.appsworld.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.telugucalendar.appsworld.Activites.HomeActivity
import com.telugucalendar.appsworld.R

class PadhaDoshaFarg : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        HomeActivity.navbar!!.visibility = View.GONE
        return inflater.inflate(R.layout.fragment_padha_dosha_farg, container, false)
    }
}