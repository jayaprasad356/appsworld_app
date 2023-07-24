package com.telugucalendar.appsworld.Activites


import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.telugucalendar.appsworld.Adapters.TempleinfoAdapter
import com.telugucalendar.appsworld.Fragments.MoreOptionsFrag
import com.telugucalendar.appsworld.Fragments.MuhurthaluFrag
import com.telugucalendar.appsworld.Fragments.PandugaluFrag
import com.telugucalendar.appsworld.Fragments.RashiPahlaluFrag
import com.telugucalendar.appsworld.Model.Templeinfo
import com.telugucalendar.appsworld.R
import com.telugucalendar.appsworld.databinding.ActivityHomeBinding
import com.telugucalendar.appsworld.helper.ApiConfig
import com.telugucalendar.appsworld.helper.Constant
import com.telugucalendar.appsworld.helper.Session
import org.json.JSONArray
import org.json.JSONObject

class HomeActivity : AppCompatActivity() {

    private var activityHomeBinding: ActivityHomeBinding? = null
    private var adView: AdView? = null
    private var interstitial: InterstitialAd? = null
    private var handler: Handler? = null
    private var activity: Activity? = null
    private var session: Session? = null
    private var i = 1

    companion object {
        var fm: FragmentManager? = null
        var navbar: BottomNavigationView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding?.root)

        fm = supportFragmentManager
        navbar = activityHomeBinding?.BottomNavigation
        activity = this@HomeActivity
        session = Session(applicationContext)

        FirebaseMessaging.getInstance().getToken().addOnSuccessListener { token ->
//            session!!.setData(
//                Constant.FCM_ID,
//                token
//            )


            sentfcmtoserver(token)

        }

        fm?.beginTransaction()?.replace(
            R.id.Container,
            com.telugucalendar.appsworld.Fragments.panchangamFrag()
        )?.commit()

        MobileAds.initialize(this, getString(R.string.admob_app_id))
        adView = findViewById(R.id.AdView)
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)

        interstitial = InterstitialAd(this)
        // interstitial?.adUnitId = "ca-app-pub-3940256099942544/1033173712" // Test Interstitial Ad Unit ID
        interstitial?.adUnitId = "ca-app-pub-5044124034987819/9441049479" // Live Interstitial Ad Unit ID
        interstitial?.loadAd(AdRequest.Builder().build())

        handler = Handler()
        handler?.postDelayed({
            displayInterstitial()
            loadInterstitial()
        }, 20000) // Load the interstitial after 30 seconds

        activityHomeBinding?.BottomNavigation?.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.Panchangam -> {
                    fm?.beginTransaction()?.replace(
                        R.id.Container,
                        com.telugucalendar.appsworld.Fragments.panchangamFrag()
                    )?.commit()
                    true
                }
                R.id.Pandugalu -> {
                    fm?.beginTransaction()?.replace(R.id.Container, PandugaluFrag())?.commit()
                    true
                }
                R.id.Muhurthalu -> {
                    fm?.beginTransaction()?.replace(R.id.Container, MuhurthaluFrag())?.commit()
                    true
                }
                R.id.RashiPhalalu -> {
                    fm?.beginTransaction()?.replace(R.id.Container, RashiPahlaluFrag())?.commit()
                    true
                }
                R.id.More -> {
                    fm?.beginTransaction()?.replace(R.id.Container, MoreOptionsFrag())?.commit()
                    true
                }
                else -> false
            }
        }
    }

    private fun sentfcmtoserver(token: String?) {


        val params = HashMap<String, String>()
        params["fcm_id"] = token.toString()
        ApiConfig.RequestToVolley({ result, response ->

            if (result) {
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {

//                        Toast.makeText(
//                            activity,
//                            jsonObject.getString(Constant.MESSAGE),
//                            Toast.LENGTH_SHORT
//                        ).show()




                    } else {
//                        Toast.makeText(
//                            activity,
//                            jsonObject.getString(Constant.MESSAGE),
//                            Toast.LENGTH_SHORT
//                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }, activity, Constant.ADD_TOKEN, params, true)



    }

    private fun loadInterstitial() {
        interstitial?.loadAd(AdRequest.Builder().build())
    }

    private fun displayInterstitial() {
        if (interstitial?.isLoaded == true) {
            interstitial?.show()
        }
    }

    override fun onDestroy() {
        handler?.removeCallbacksAndMessages(null) // Remove any pending tasks
        super.onDestroy()
    }
}
