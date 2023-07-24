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
import com.telugucalendar.appsworld.Adapters.NamakaranamAdapter
import com.telugucalendar.appsworld.Model.Namakaranam
import com.telugucalendar.appsworld.databinding.FragmentNamaKaranamBinding
import com.telugucalendar.appsworld.helper.ApiConfig
import com.telugucalendar.appsworld.helper.Constant
import com.telugucalendar.appsworld.helper.DatabaseHelper
import org.json.JSONArray
import org.json.JSONObject

class NamaKaranamFrag : Fragment() {

    private var binding : FragmentNamaKaranamBinding? = null;
    var databaseHelper: DatabaseHelper? = null
    var activity: Activity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        HomeActivity.navbar!!.visibility = View.GONE
        // Inflate the layout for this fragment
        if(HomeActivity.navbar!!.visibility == View.GONE) {
            HomeActivity.navbar!!.visibility = View.VISIBLE
        }

        binding = com.telugucalendar.appsworld.databinding.FragmentNamaKaranamBinding.inflate(layoutInflater,container,false)
        activity = getActivity()

        databaseHelper = DatabaseHelper(activity)


        binding!!.recyclerView.setLayoutManager(
            LinearLayoutManager(
                activity,
                LinearLayoutManager.VERTICAL,
                false
            )
        )

        namakaranam();

        return binding!!.root

    }

    private fun namakaranam() {
        val params = HashMap<String, String>()
        params[Constant.NAMAKARAM] = "1"
        ApiConfig.RequestToVolley({ result, response ->
            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        val jsonArray: JSONArray = jsonObject.getJSONArray(Constant.NAMAKARAM_LIST)
                        val g = Gson()
                        val namakaranam: ArrayList<Namakaranam> = ArrayList<Namakaranam>()
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject1 = jsonArray.getJSONObject(i)
                            if (jsonObject1 != null) {
                                val group: Namakaranam =
                                    g.fromJson(jsonObject1.toString(), Namakaranam::class.java)
                                namakaranam.add(group)
                            } else {
                                break
                            }
                        }
                        val adapter = NamakaranamAdapter(requireActivity(),namakaranam)
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