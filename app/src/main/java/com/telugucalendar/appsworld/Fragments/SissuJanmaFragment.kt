package com.telugucalendar.appsworld.Fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.telugucalendar.appsworld.Activites.HomeActivity
import com.telugucalendar.appsworld.Adapters.SissujunmaAdapter
import com.telugucalendar.appsworld.Model.Sissujanma
import com.telugucalendar.appsworld.databinding.FragmentSissuJanmaBinding
import com.telugucalendar.appsworld.helper.ApiConfig
import com.telugucalendar.appsworld.helper.Constant
import com.telugucalendar.appsworld.helper.DatabaseHelper
import org.json.JSONArray
import org.json.JSONObject

class SissuJanmaFragment : Fragment() {
    private var binding : FragmentSissuJanmaBinding? = null;
    var databaseHelper: DatabaseHelper? = null
    var activity: Activity? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        HomeActivity.navbar!!.visibility = View.GONE
        // Inflate the layout for this fragment
        if(HomeActivity.navbar!!.visibility == View.GONE) {
            HomeActivity.navbar!!.visibility = View.VISIBLE
        }

        binding = FragmentSissuJanmaBinding.inflate(layoutInflater,container,false)
        activity = getActivity()

        databaseHelper = DatabaseHelper(activity)


        binding!!.recyclerView.setLayoutManager(
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false
            )
        )

        sissujanma();

        return binding!!.root
    }

    private fun sissujanma() {
        val params = HashMap<String, String>()
        params[Constant.SISSU_JANMA] = "1"
        ApiConfig.RequestToVolley({ result, response ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        val jsonArray: JSONArray = jsonObject.getJSONArray(Constant.SISSU_JANMA_LIST)
                        val g = Gson()
                        val sissujanma: ArrayList<Sissujanma> = ArrayList<Sissujanma>()
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject1 = jsonArray.getJSONObject(i)
                            if (jsonObject1 != null) {
                                val group: Sissujanma =
                                    g.fromJson(jsonObject1.toString(), Sissujanma::class.java)
                                sissujanma.add(group)
                            } else {
                                break
                            }
                        }
                        val adapter = SissujunmaAdapter(requireActivity(), sissujanma)
                        binding!!.recyclerView.setAdapter(adapter)
                    } else {
                        Toast.makeText(
                            activity,
                            jsonObject.getString(Constant.MESSAGE),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }, activity, Constant.ALLDATALIST_URL, params, true)

    }


}