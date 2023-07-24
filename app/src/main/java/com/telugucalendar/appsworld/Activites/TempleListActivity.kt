package com.telugucalendar.appsworld.Activites
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.telugucalendar.appsworld.Adapters.AudioLiveAdapter
import com.telugucalendar.appsworld.Adapters.TempleinfoAdapter
import com.telugucalendar.appsworld.Model.Templeinfo
import com.telugucalendar.appsworld.R
import com.telugucalendar.appsworld.helper.ApiConfig
import com.telugucalendar.appsworld.helper.Constant
import org.json.JSONArray
import org.json.JSONObject

class TempleListActivity : AppCompatActivity() {

    var activity: Activity? = null
    lateinit var recyclerView:RecyclerView
    lateinit var templeinfoAdapter: TempleinfoAdapter
    var audioLiveAdapter: AudioLiveAdapter? = null




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temple_list)

        activity = this@TempleListActivity


       recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        var imgbackbttn = findViewById<ImageView>(R.id.imgbackbttn);

        imgbackbttn.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })




        recyclerView!!.setLayoutManager(LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false))




        templelist()
    }

    private fun templelist() {
        Log.d("Templeinfo", Constant.TEMPLEINFO_LIST)

        val params = HashMap<String, String>()
        ApiConfig.RequestToVolley({ result, response ->

            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {

                        val jsonArray: JSONArray = jsonObject.getJSONArray(Constant.DATA)
                        val g = Gson()
                        val templeinfo: ArrayList<Templeinfo> = ArrayList<Templeinfo>()
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject1 = jsonArray.getJSONObject(i)
                            if (jsonObject1 != null) {
                                val group: Templeinfo =
                                    g.fromJson(jsonObject1.toString(), Templeinfo::class.java)
                                templeinfo.add(group)
                            } else {
                                break
                            }
                        }
                        val adapter = TempleinfoAdapter(this@TempleListActivity, templeinfo)
                        recyclerView.setAdapter(adapter)
                        templeinfoAdapter.notifyDataSetChanged()




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
        }, activity, Constant.TEMPLEINFO_LIST, params, true)


    }
}